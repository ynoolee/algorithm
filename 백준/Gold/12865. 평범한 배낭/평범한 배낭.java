

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static int n;
    public static int k;
    public static int[][] dp ;
    // [n+1][k+1] 로 해야함 . 왜냐하면, 현재 무게 j 에서 - w[i]를 뺏을 때 0이 나오는 경우가 있을 수 있거든 .
    // 따라서 해당 경우를 따로 다뤄줘야하므로, 0으로 채워진 column이 맨 좌측에 존재한다고 생각
    public static int[][] items ;
    //public static ArrayList<int[]> items = new ArrayList<>(101);
    // items.get(i)[0]는 weight, [1]은 value
    public static void Setting() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n+1][k+1];
        items = new int[n][2];
        int w=0,v=0;
        // n 개의 물건에 대하여
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            items[i][0]  = Integer.parseInt(st.nextToken());
            items[i][1]  = Integer.parseInt(st.nextToken());
            //items.add(i,new int[]{w,v});

        }
    }
    public static void dp(){
        for(int j=1;j<=k;j++){
            // item 저장은 0부터 해서 n개를 해 놓았지만,
            // dp에서는 i가 0 인 것을 1 인 것으로 다루도록 한다 .
            // 왜냐하면, 첫 번 째 item에 대해서도  dp[i][j]= dp [i-1][j]를 할 수가 있어야 하기 때문.
            for(int i=0;i<n;i++){
                if(items[i][0]<=j){
                    // i 번째 item을 선택하는 것이 가능한 경우
                    dp[i+1][j]= Math.max(dp[i][j],dp[i][j-items[i][0]]+items[i][1]);
                }else{
                    // i 번째 item을 선택하는 것이 불가능한 경우
                    dp[i+1][j] = dp[i][j];
                }
            }
        }
        // 따라서 최대값은 무조건 dp[n][k]에 저장되어있다.
        System.out.println(dp[n][k]);
    }
    public static void main(String[] args) throws IOException {
        Setting();
        dp();
    }

}