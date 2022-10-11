
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private StringTokenizer st;

    /**
     * 이 문제에서는 x 가 행, y 를 열로서 사용하고 있다는 특징을 갖고 있다. ( 나는 헷갈렸음 )
     * */
    private void run() throws Exception {
        int n =0; int m = 0;
        int[][] board; int[][] cache;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1]; // x = 0 인 모든 열, y = 0 인 모든 행은 0 으로 채워넣는다.

        // fill board
        for(int r = 1; r <= n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= n ;c++) {
                int temp = Integer.parseInt(st.nextToken());
                board[r][c] = temp;
            }
        }
        cache = makeCache(board);
        // questions
        for(int q = 0; q < m ;q++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            System.out.println(calculate(x1,x2,cache,y1,y2));
        }
    }
    /**
     * 누적합으로 채워넣는다 
     * -> cache[x][y] 는 [x][y] 를 가장 오른쪽 아래 꼭지점으로하는, 가장 왼쪽 위 꼭지점은 [0][0]인 직사각형 으로 생각하면 된다 
     * -> (x2,y2) ~ (x1,y1)  은 이 누적합 값들을 사용해서 풀 수 있다.
     * 이런식으로 누적합을 cache 해 두고 사용하는 문제들도 꽤 많은 것 같다.
     * */
    private int[][] makeCache(int[][] board) {
        int n = board.length;
        int[][] cache = new int[n][n];

        for(int r = 1; r < n ; r++) {
            for(int c = 1; c < n; c++) {
                cache[r][c] = cache[r][c-1] + cache[r-1][c] - cache[r-1][c-1] + board[r][c];
            }
        }

        return cache;
    }

    private int calculate(int x1 , int x2, int[][] cache, int y1, int y2) {
        return cache[x2][y2] - cache[x1 - 1][y2] - cache[x2][y1 - 1] + cache[x1 -1][y1-1];
    }
    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
