import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    /**
     * 우왕 또 머리가 안돌아가는 날이었다; ㅠ-ㅠ
     * 쉬운 dp 인데 또 subproblem 파악이 안되고, brute force 도 떠오르지가 않았다.
     * */
    // 그냥 떠오른 것은..
    // dp[i] 는,
    // i 번째에 왔는데, 이 때의 부분 수열상 마지막 값이 prev 일 때, i ~ len -1 로 만들 수 있는 '합이 최대인 부분수열의 합'

    private int[][] dp;

    public void run() throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        dp = new int[1001][n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = recur(0, arr, 0);
        System.out.println(ans);

    }


    private int recur(int i, int[] arr, int prev) {
        if(i == arr.length) return 0;
        if(dp[prev][i] != 0) return dp[prev][i];

        int included = 0; int excluded = 0;

        if(arr[i] > prev) {
            included = recur(i + 1, arr, arr[i]) + arr[i];
        }

        excluded = recur(i + 1, arr, prev);

        return dp[prev][i] = Math.max(included, excluded);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run();
    }
}