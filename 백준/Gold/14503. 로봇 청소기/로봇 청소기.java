
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private int[] opposite = new int[]{2, 3, 0, 1};

    private int[] left = new int[]{3, 0, 1, 2};

    private void run() throws Exception {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] b = new int[n][m];
        int[] cur = new int[2];
        int dir = 0; // 현재 위치, 방향

        st = new StringTokenizer(br.readLine());
        cur[0] = Integer.parseInt(st.nextToken());
        cur[1] = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = solution(b, n, m, cur, dir);
        System.out.println(res);

    }

    // 청소 칸 : 2
    private int solution(int[][] b, int n, int m, int[] c, int cdir) {
        int cur[] = new int[]{c[0], c[1]};
        int dir = cdir;
        int cnt = 0;

        while (true) {
            boolean clear = false;

            // 아직 청소 되어있지 않은 칸이라면 청소하기
            if(b[cur[0]][cur[1]] == 0 ) {
                b[cur[0]][cur[1]] = 2;
                cnt++;
            }
            
            // 네 방향이 이미 청소되어있거나 벽인 경우, 후진 or break 해야함. 이때 중요한게 "현재 방향"을 기억하고 있어야 한다는 것
            // 따라서 tempDir 과, 임시의 "다음 좌표 r, c" 를 위한 변수를 사용한다
            int tempDir = dir;
            int nr = cur[0];
            int nc = cur[1];
            for (int i = 0; i < 4; i++) {
                tempDir = left[tempDir];
                nr = cur[0] + dirs[tempDir][0];
                nc = cur[1] + dirs[tempDir][1];

                // 청소가 아직 안되어 있다면
                if (!isImPossible(b, nr, nc, n, m)) {
                    clear = true;
                    break;
                }
            }

            // 청소를 했었다면
            if (clear) {
                // 방향과, 현재 칸을 "실제로" 업데이트
                dir = tempDir;
                cur[0] = cur[0] + dirs[dir][0];
                cur[1] = cur[1] + dirs[dir][1];
            } else {
                // 네 방향 모두 돌고 왔다면 - 후진과 관련된 무언가를 하지
                // 후진 방향으로 한 칸 갔을 때의 칸 상태를 기준으로 뭔가 하니까 이거를 임시로 구해놓아야함 (실제 cur 을 업데이트 해버리면 안되고)
                tempDir = opposite[dir];
                nr = cur[0] + dirs[tempDir][0];
                nc = cur[1] + dirs[tempDir][1];

                if (nr < 0 || nc < 0 || nr >=n || nc >= m || b[nr][nc] == 1) {
                    break;
                } else {
                    // 실제 칸 cur 업데이트는 이 때...
                    cur[0] = nr;
                    cur[1] = nc;
                }
            }

        }

        return cnt;
    }

    // 벽 또는 이미 청소된 곳 or 범위를 넘는 곳 이라면 true
    private boolean isImPossible(int[][] b, int r, int c, int n, int m) {
        return r >= n || c >= m || r < 0 || c < 0 || b[r][c] == 1 || b[r][c] == 2;
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
