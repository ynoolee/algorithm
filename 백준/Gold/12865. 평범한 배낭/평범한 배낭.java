
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private int[][] dp;

    private int[] v;

    private int[] w;

    private int n, k;

    private void run() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n][100001]; // dp 배열 생성

        v = new int[n]; // 가치 배열
        w = new int[n]; // 무게 배열

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        int result = recur(0, 0);
        System.out.println(result);
    }

    // i 번째 물건을 선택하러 왔을 당시까지의 무게
    
    private int recur(int i, int curW) {
        if( i == n ) return 0;
        if (dp[i][curW] != 0) {
            return dp[i][curW];
        }

        int c1 = 0;
        // 포함
        if (curW + w[i] <= k) {
            c1 = recur(i + 1, curW + w[i]) + v[i];
        }
        // 미 포함
        int c2 = recur(i + 1, curW);

        return dp[i][curW] = Math.max(c1, c2);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }

}
