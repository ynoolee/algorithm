import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private void run() throws Exception {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());

        }

        System.out.println(bottomUp(arr, k, n));
    }


    /*
    * 딱 k 원을 만들어야 한다.
    * 종류도 몇 가지가 주어질지 알 수가 없다
    * 1 ~ k 까지 각 수에 대해, 만들 수 있는 경우를 채워 나가는 방식이 필요하다
    * 무슨 말이냐면, 합이 k 가 되도록 하고 싶고, 현재 나는 coins[i] 원을 손에 쥐고 있다고 해 보자
    * 그렇다면 dp[k - coins[i]] 만큼의 경우의 수를 dp[k] 에 더해 주면 되는 것이다.
    * 왜냐하면 지금 손에 쥐고 있는 coins[i] 를 더했을 때 k 가 될 수 있는 상황에
    *  k - coins 를 만들 수 있는 경우의 수 가 포함되기 때문이다.
    *  따라서 각 코인은 한 번씩만 돌아야 한다
    * 또한, 첫 번째 코인은 dp[k-coins[i]] 를 했을 때 아마 dp[0] 인 경우일 것이다. 따라서 dp[0] 은 1을 세팅 해 놓아야 한다
    * */
    private int bottomUp(int[] coins, int k, int n) {
        int[] dp = new int[k + 1];
        dp[0] = 1;

        // coins 가 가장 바깥 쪽 for loop 로서 돌아야 한다
        for(int i = 0; i < n; i++) {
            // coins[0] ~ coins[i] 를 사용해서
            // s - coins[i] >=0 이 되는 첫 번째 s 부터 시작해야함 -> coins[i]
            for(int s = coins[i]; s <= k ; s++ ) {
                // s 를 만들 수 있는 경우의 수
                dp[s] += dp[s- coins[i]];
            }
        }

        return dp[k];
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run();
    }
}