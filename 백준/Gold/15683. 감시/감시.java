
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {0, -1}, {1, 0}}; // 북, 동, 서, 남

    private List<List<Integer>>[] cctv;

    private void initCctv() {
        cctv = new ArrayList[6]; // 번호는 1 번에서 6 번이니까

        cctv[1] = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            cctv[1].add(new ArrayList<>());
        }
        cctv[1].get(0).add(0);
        cctv[1].get(1).add(1);
        cctv[1].get(2).add(2);
        cctv[1].get(3).add(3);

        cctv[2] = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            cctv[2].add(new ArrayList<>());
        }
        cctv[2].get(0).add(0);
        cctv[2].get(0).add(3);
        cctv[2].get(1).add(1);
        cctv[2].get(1).add(2);

        cctv[3] = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            cctv[3].add(new ArrayList<>());
        }
        cctv[3].get(0).add(0);
        cctv[3].get(0).add(1);
        cctv[3].get(1).add(0);
        cctv[3].get(1).add(2);
        cctv[3].get(2).add(3);
        cctv[3].get(2).add(2);
        cctv[3].get(3).add(3);
        cctv[3].get(3).add(1);

        cctv[4] = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cctv[4].add(new ArrayList<>());
        }
        cctv[4].get(0).add(0);
        cctv[4].get(0).add(1);
        cctv[4].get(0).add(2);
        cctv[4].get(1).add(0);
        cctv[4].get(1).add(1);
        cctv[4].get(1).add(3);
        cctv[4].get(2).add(0);
        cctv[4].get(2).add(2);
        cctv[4].get(2).add(3);
        cctv[4].get(3).add(1);
        cctv[4].get(3).add(2);
        cctv[4].get(3).add(3);

        cctv[5] = new ArrayList<>();
        cctv[5].add(new ArrayList<>());
        cctv[5].get(0).add(0);
        cctv[5].get(0).add(1);
        cctv[5].get(0).add(2);
        cctv[5].get(0).add(3);
    }

    private List<int[]> all = new ArrayList<>(); // 모든 cctv 들을 여기다가 넣어

    private int min = Integer.MAX_VALUE; // max 비사각 지대 ( min 사각 지대 = n * m - max 비사각지대 )

    private void run() throws Exception {
        initCctv();

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] b = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
                if (b[i][j] > 0 && b[i][j] < 6) {
                    all.add(new int[]{i, j, b[i][j]});
                }
            }
        }

        next(b, n, m, 0);

        invisible(b, n, m);

        System.out.println(min);
    }


    // cctv 를 먼저 list 에 집어 넣고, 하나씩 빼는 방식으로 해 봐야 겠다.
    // 이전 방법은 cctv 위치를 덮어씌어 버려서(따라서 해당 cctv 의 위치 r,c 과 cctv번호 까지 다 넣어놔야함 ) , cctv 위치 부터 시작하는 뭔가를 하지 못하게 되었었다
    // list 에 넣고.. 꺼내...쓰기..
    // 백트랙킹
    private void next(int[][] b, int n, int m, int i) {
//        System.out.println(String.format("n : %d, m : %d, i : %d", n, m, i));
        if (i == all.size()) {
            int invisible = invisible(b, n, m);
            min = Math.min(min, invisible);

            return;
        }

        int[] locations = all.get(i);
        int cur = locations[2]; // 현재 위치의 값
        // CCTV 인 칸

        for (List<Integer> comb : cctv[cur]) {
            // 기존의 보드를 복사해 새로운 보드 생성
            int[][] cb = copy(b, n, m);
            // 각 방향을 선택할 때 마다 달라져야 하기 때문
            for (Integer dir : comb) {
                fill(dir, locations[0], locations[1], cb, n, m);
            }

            next(cb, n, m, i + 1);
        }


    }

    /*
     * ?? 뭐지?? 이 문제에서는 '6' 도 '비사각 지대' 로 여기고 있다
     * '0' 인 공간들을 '사각 지대'로 생각하는 것이다.
     * 현재 내 풀이는 '전체 - 비사각지대' 를 빼는 방법을 취하고 있다.
     * 그런데 6 은 마주칠 때만 break 하는 방식이라.. 때 때 마다 카운트 될 수도 아닐 수도 있을 듯 하다.
     * 그렇다고 next 함수에서 일일이 하자니, 더 헷갈린다.
     * 어차피 6은 비사각지대라면, 그냥 마지막에 한 번만 전체 6 의 개수를 세어주고, 이를 전체에서 빼주도록 하겠다
     * */
    private int invisible(int[][] b, int n, int m) {
        int cnt = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (b[r][c] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }


    // 비 사각 지대 -> 7 로 표시
    // 각 방향으로, 현재 위치에서부터 시작해서 채워 넣기
    // 벽(6) 을 만나면 멈춘다
    // 벌써 "비사각 지대인 칸" 은 넘긴다 ( count 에 세면 안됨)
    // cctv 인 칸도 7 로 채운다
    private void fill(int dir, int prevR, int prevC, int[][] b, int n, int m) {
        int r = prevR;
        int c = prevC;

        while (r < n && c < m && r >= 0 && c >= 0) {
            if (b[r][c] == 6) {
                break;
            } else if (b[r][c] >= 0 && b[r][c] < 6) {
                b[r][c] = 7;
            }

            r = r + dirs[dir][0];
            c = c + dirs[dir][1];
        }
    }

    private int[][] copy(int[][] b, int n, int m) {
        int[][] board = new int[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = Arrays.copyOf(b[i], m);
        }

        return board;
    }


    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
