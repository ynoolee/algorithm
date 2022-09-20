import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private int[] c = new int[1001]; // 각 카드의 비용
    private int[] dp = new int[1001]; // 각 개수별 최대비용

    private void run() throws IOException {
        int ans = 0;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n ;i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        ans = recur(n);
        System.out.println(ans);
    }

    // 최대 비용을 리턴한다
    // cnt : 현재까지 cnt 만큼의 개수가 남아있다( 사야 한다 ) - w 는 필요 없었다-그저 리턴에만 사용하면된다. 이 문제는 중복해서 선택이 가능하기 때문에, 그저 남아있는 사야할 카드의 개수를 중심으로 subproblem 이 중복된다 
    private int recur(int cnt) {
        if(cnt == 0) {
            return 0;
        }
        if(dp[cnt] != 0) return dp[cnt];


        int max = Integer.MIN_VALUE;
        // cnt 이하의 개수를 가진 팩들에 대해 iterate 하며 조합을 생성한다
        for(int i = 1; i <= cnt; i++) {
            max = Math.max(max,recur(cnt - i) + c[i]);
        }

        return dp[cnt] = max;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }

}