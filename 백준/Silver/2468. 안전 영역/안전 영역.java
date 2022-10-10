import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private StringTokenizer st;

    private int[][] board;
    private int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    private void run() throws Exception {
        int ans = 0;
        int n = Integer.parseInt(br.readLine());
        int max = 0; // 입력된 지역들의 높이 중 최대 높이

        board = new int[n][n];
        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < n;c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                max = Math.max(max, board[r][c]);
            }
        }

        // 장마철, 물에 잠기는 높이는 0 ~ max-1 까지로 하자 => h 이하의 지역은 모두 물에 잠긴다
        // 0 을 포함한 이유는, 어떤 지역도 물에 잠기지 않는 경우를 위함이다(지역들은 1이상의 높이를 가짐) 
        for(int h = 0 ; h < max; h++) {
            ans = Math.max(ans, full(h,n));
        }

        System.out.println(ans);
    }

    // h 이하인 지역은 모두 잠긴다고 하였을 때, 잠기지 않는 영역의 개수를 리턴한다
    private int full(int h, int n) {
        boolean[][] visit = new boolean[n][n];
        int cnt = 0;

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(board[r][c] > h && !visit[r][c]) {
                    dfs(r,c,n,h,visit);
                    cnt++;
                }

            }
        }
        
        return cnt;
    }

    // h 초과인 애들은 모두 visit 으로 체크한다
    private void dfs(int r, int c, int n, int h, boolean[][] visit) {
        visit[r][c] = true;

        for(int dir = 0; dir < dirs.length; dir++) {
            int nr = r + dirs[dir][0];
            int nc = c + dirs[dir][1];

            if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if(visit[nr][nc] || board[nr][nc] <= h) continue;

            dfs(nr,nc,n,h,visit);
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}