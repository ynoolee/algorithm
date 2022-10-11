import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private StringTokenizer st;

    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1},
        {0, 1}}; // 위(0), 아래(1), 왼쪽(2), 오른쪽(3)

    private void run() throws Exception {
        int n = 0;
        int m = 0;
        int r = 0;
        int[][] board;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                board[row][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < r; i++) {
            board = rotate(board);
        }

        print(board);
    }

    private void print(int[][] board) {
        int r = board.length;
        int c = board[0].length;

        for (int[] arr : board) {
            for (int e : arr) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }


    // rc -> 회전하기 전의 배열의 row 수(n),col 수(m)
    private int[][] rotate(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] next = new int[n][m]; // 회전한 결과 나오는 배열
        int r = Math.min(n, m) / 2; // 이 r 은, 껍질의 개수
        int cnt = 0;
        while (cnt < r) {
            // 아래로 이동
            move(cnt, cnt, 1, board, next, n - cnt * 2 - 1);
            // 오른쪽
            move(n - 1 - cnt, cnt, 3, board, next, m - cnt * 2 - 1);
            // 위
            move(n - 1 - cnt, m - 1 - cnt, 0, board, next, n - cnt * 2 - 1);
            // 왼쪽
            move(cnt, m - 1 - cnt, 2, board, next, m - cnt * 2 - 1);

            cnt++;
        }

        return next;
    }

    // cnt : 몇 번째 껍질
    // tempCnt : 이동시킬 원소가 tempCnt 개 존재한다.
    private void move(int y, int x, int dir, int[][] board, int[][] next, int tempCnt) {
        int cy = y;
        int cx = x;
        int ny = y;
        int nx = x;
        int cur = 0;
        while (cur < tempCnt) {
            ny = cy + dirs[dir][0];
            nx = cx + dirs[dir][1];

            next[ny][nx] = board[cy][cx];
            cy = ny;
            cx = nx;
            cur++;
        }
    }


    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
