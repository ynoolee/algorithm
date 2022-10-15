import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private StringTokenizer st;

    private void run() throws Exception {
        /**
         * 수의 자리가 오름차순을 이루는 수
         * 인접한 수가 "같아도 오름차순" 으로 친다.
         * 수의 "길이" 가 주어졌을 때, 오르막 수의 개수 구하기
         * 수는 0 으로 시작할 수도 있다. 길이는 최대 1000이 주어진다
         * */
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int first = 0; first < 10; first++) {
            cnt = ( cnt + dp(0, first, n)) % 10_007;
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
        bw.close();
    }

    private void naive(int n) {
        naiveRecur(0, 0, n);
    }
    private int cnt = 0;

    // 이전 자리의 수가 pre 이고, 현재 ith 번째 수를 선택하려고 하는 경우
    private void naiveRecur(int pre, int ith, int n) {
        if(ith == n) {
            cnt = (cnt +1) % 10_007;
            return;
        }

        for(int i = pre; i < 10 ; i++) {
            naiveRecur(i, ith + 1, n);
        }
    }

    // i 번째 수가 j 일 때, 오르막 수가 될 수 있는 경우의 수는 0 ~ i 번째 까지 어떤 수였던 간에 중복되는 subProblem 으로 볼 수 있다
    // dp[i][j] 는 i 위치에 온 수가 j 일 때, 오르막 수 가 될 수 있는 경우의 수
    private int[][] dp = new int[1000][10];
    private int dp(int i, int j, int n) {
        if(n - 1 == i) return 1;
        if(dp[i][j] != 0) return dp[i][j];

        int cnt = 0;

        for(int next = j; next < 10; next++) {
            cnt = (cnt + dp(i + 1, next, n)) % 10_007;
        }
        return dp[i][j] = cnt;
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}