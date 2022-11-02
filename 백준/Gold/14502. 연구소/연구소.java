
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;
    /**
     * 이 문제는 진짜 감도 못잡았다 ㅠㅠ.. 막아야 하는 칸을 어떤 기준으로 정해야 하는 것인지 파악이 안되었다
     * BFS 를 통해, 하나의 depth 까지 진행했을 때, q 에 남아있는 개수로라도 판단할 수 있나?? 했는데 내 눈에는 전혀 안 보였다.
     * 그렇다면 벽을 세울 수 있는 모든 경우의 수를 고려해야하나..
     * 한 시간 정도 고민하다 다른 사람 풀이를 봤다
     *
     * 정말 "벽을 세울 수 있는 모든 경우의 수"를 고려한 풀이를 보았다.
     * 이 문제는 행, 열이 최대 8 이기 때문에 -> 총 64칸이 존재한다.
     * 64 칸 중, 3 개의 칸을 뽑는 것으로 64 * 63 * 62 = 25만 정도로 해 볼만한 경우의 수다
     * - 그런데 이 이후 bfs 로 탐색을 진행해야 한다 -> bfs 는 O(N) 임
     * -> 약 64 * 64 * 63 * 62 으로 천만정도
     *
     * 1. 0 인 칸이 존재하면 거기서부터 벽을 세운다. -> 재귀함수를 사용해서 순차적으로 3 개의 벽을 세운다
     * 2. 벽을 세운 후 바이러스를 퍼트린다
     * 3. 바이러스가 퍼진 이후 빈칸의 개수를 세어본다
     *
     * 각각의 벽을 세운 경우에 대해  bfs 를 해야한다.
     * 다음 경우를 수행할 때면, 다시 복원해야하는데, 이게 더 어려울 수도 있음.
     *
     * 따라서 매번 임시 테이블을 만들어 진행한다
     * */

    private void run() throws Exception {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] init = new int[n][m];

        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < m; c++) {
                init[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        every(init, n, m);
        System.out.println(max);
    }

    private int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    // 모든 경우를 수행 해 볼 함수
    private void every(int[][] init, int n, int m) {

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                // 빈 칸 인 경우, 벽을 세워보자
                if(init[r][c] == 0) {
                    int[][] temp = temp(init);
                    temp[r][c] = 1;                    // 벽을 세우고
                    addWall(1, temp, n, m);
                    temp[r][c] = 0;                     // 벽 해제 하기
                }
            }
        }

    }

    // 현재 상황에서, 바이러스를 퍼트린다.
    private int spread(int[][] board, int n, int m, boolean[][] visit) {
        int sum = 0;

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(board[r][c] == 2 && !visit[r][c]) {
                    bfs(board, visit, r, c, n, m);
                }
            }
        }

        return sum;
    }

    private void bfs(int[][] board, boolean[][] visit, int r, int c, int n, int m) {
        Deque<int[]> q = new ArrayDeque<>();
        visit[r][c] = true;
        q.add(new int[]{r,c});

        // 바이러스들을 큐에 넣는다.
        for(int cr = 0; cr < n; cr++) {
            for(int cc = 0; cc < m; cc++) {
                if(board[cr][cc] == 2 && !visit[cr][cc]) {
                    q.add(new int[]{cr,cc});
                    visit[cr][cc] = true;
                }
            }
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int dir = 0; dir < dirs.length; dir++) {
                int nr = cur[0] + dirs[dir][0];
                int nc = cur[1] + dirs[dir][1];

                if(nr < 0 || nc < 0 || nr >= n || nc >= m)  continue;

                // 빈칸인 경우
                if(board[nr][nc] == 0 && !visit[nr][nc]) {
                    // 바이러스를 퍼트린다 -> 실제로 값을 변경하진 않고 visit 으로 대체
                    visit[nr][nc] = true;
                    q.add(new int[]{nr,nc});// 퍼트려진 칸 근접한 칸들로도 전파될 수 있다
                }
            }
        }
    }



    // temp array 에 복사할 거임
    private int[][] temp(int[][] original) {
        int[][] temp = new int[original.length][original[0].length];

        for(int r = 0; r < original.length; r++) {
            System.arraycopy(original[r], 0, temp[r], 0, original[r].length);
        }

        return temp;
    }

    private int max = 0;

    // cnt 가 3 이 될 때 까지 벽을 세울 함수
    private void addWall(int cnt, int[][] board, int n, int m) {
        if(cnt == 3) {
            boolean[][] visit = new boolean[n][m];
            // 바이러스를 퍼트리고
            spread(board, n, m, visit);
            // 안전구역의 개수를 세어보자
            int safe = 0;
            for(int r = 0; r < n; r++) {
                for(int c = 0; c < m; c++) {
                    if(board[r][c] == 0 && !visit[r][c]) safe++;
                }
            }

            max = Math.max(max, safe);
            return;
        }
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                // 빈 칸 인 경우, 벽을 세워보자
                if(board[r][c] == 0) {
                    board[r][c] = 1;
                    addWall(cnt + 1, board, n, m);
                    board[r][c] = 0;
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
