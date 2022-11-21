import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private final int INIT = 10001; // Integer.MAX_VALUE 를 할 경우 틀렸습니다가 나온다

    private void run() throws Exception {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());

        }

        int res = bottomUp(arr, n, k);

        if(res == INIT) {
            System.out.println("-1");
        } else {
            System.out.println(res);
        }
    }

    /**
     *
     * 동전 1 문제와 같이 생각했다
     * - 먼저 , 아래와 같은 바텀업 방식은 중복되는 subproblem 을 만날 상황이 존재하지 않는다
     * 동전 1 문제에서도, 각각의 동전을 iterate 하면서
     * 0 ~ i 번째까지의 동전을 가지고, s 를 만들 수 있는 경우의 수를 업데이트 하는 방식을 사용했다
     * - 여기서는!! dp[s] 를, s 를 만드는데 사용되는 경우의 수가 아닌, s 를 만드는데 드는 최소의 동전의 개수를 저장하는 것을 생각했다
     * 그렇다면 i 번째 동전을 쥐고 있을 경우 s 를 만들기 위해서는 , dp[s-coins[i]] 에다가 + 1 을 하게 되는 것이다.
     * 그런데 이 때, 이렇게 해서 업데이트 한 dp[s] 값은, dp[s - coins[i]] 보다 작거나 같아야 한다.
     *
     * */


    private int bottomUp(int[] coins, int n, int k) {
        int[] dp = new int[k + 1];
        Arrays.fill(dp, INIT);
        dp[0] = 0; // 이번에는 개수이고, 현재동전을 사용해서 만들 수 있는 경우라면 + 1 을 해야하므로 dp[0] = 0 으로 세팅하고 시작해야 한다

        for(int i = 0; i < n; i++) {
            if(coins[i] > k) continue;
            // s 를 coins[i] 부터 increment 시키면서 dp 를 업데이트하면
            // 예를들어 3,6,9 에 대한 코인이 차례차례 1,2,3 개가 필요하다는 사실을 업데이트 하는것이 가능하다
            // dp[s] 를 구하기 위해 이미 구해놓은 dp[s-coins[i]] 를 사용하는 것이다
            for(int s = coins[i]; s <= k; s++) {
                // INIT 값을 Integer.MAX_VALUE 로 초기화 해 둘 경우 , dp[s-coins[i]] 가 INIT 인 경우 오버플로우가 발생해 최소값이 업데이트 되어버린다
                // 따라서 INIT 은 k + 1 정도로 세팅해두는 것이 필요하다
                dp[s] = Math.min(dp[s], dp[s - coins[i]] + 1); // 현재 손에 쥔 동전 coins[i] 를 사용하는 경우니까 + 1 을 해야 함.
            }
        }

        return dp[k];
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run();
    }
}