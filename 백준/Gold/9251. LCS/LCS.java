import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static String str1,str2;
    public static int[][] dp = new int[1001][1001];
    //public static element[][] cache = new element[1001][1001];
    //public static char[] res = new char[1000];


    public static void Setting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
    }
    public static void solve(){
        char cur1=0,cur2=0;
        for (int i=0;i<str2.length();i++){
            cur1 = str2.charAt(i);
            for(int j=0;j<str1.length();j++){
                cur2 = str1.charAt(j);
                if(cur1==cur2)  dp[i+1][j+1] =dp[i][j]+1;
                else {
                    dp[i+1][j+1]=Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        // 답은 항상, dp[str2.length()][str1.length()] 에 있다.
        System.out.println(dp[str2.length()][str1.length()]);
    }

    public static void main(String[] args) throws IOException {
        Setting();
        solve();
    }
}