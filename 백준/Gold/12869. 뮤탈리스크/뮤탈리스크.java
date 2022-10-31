
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    /**
     * @@  문제 풀이에 대한 고민 @@
     * Greedy 인가? 했으나 아니었음.
     * @ 생각했던 Greedy -> 남아있는 체력순으로 내림차순해서, 체력 많은 애 부터 9,3,1 을 빼면 되겠지
     * @@ 이렇게 생각할 경우, 문제 예시는 3번의 공격이 필요하게 된다.
     * ---- 12, 10, 4 에서 9,3,1을 빼면 3,7,3 -> 이를 정렬하면 7,3,3,
     * ---- 7,3,3 에서 9,3,1 을 빼면 파괴,파괴,2
     * ---- 파괴,파괴,2 에서 남은 체력 2인 SVC 까지 파괴하려면 한 번더 해야함.
     * ---- 이렇게 총 3번의 공격이 필요함.
     * ---- 하지만 정답은 2번임
     * @ 위의 방식으로는 할 수 없는 것. 즉 다양한 경우를 고려해 봐야 하는 것
     * @ 이러면 DP 인가 싶어진다. 아마도 남아있는 체력으로 DP 의 캐시를 만들어야 할 것 같다
     * @ 그러면서도 Greedy 도 섞여 있을 것이다. 2개의 SVC 가 남아있다면 9,3 으로 공격해야지 굳이 3,1 로 공격할 필요 없으니까..
     * @ --> 앗 근데 그리디로 하려니까 경우의 수를 너무 다 나눠봐야하는 것 같았다... 그래서 생각을 바꿈.
     * @ --> 항상 3개의 체력이 전달되는 것으로 하기로 함. 단, 0보다 작아져도 0이 전달되도록 하기로 하였음. 이를 위해 calc 함수를 추가함. 
     * */
    private int[][][] dp = new int[61][61][61]; // 가장 많은 체력이 남은, 중간 체력, 가장 적은 체력
    private void run() throws Exception {
        int n = Integer.parseInt(br.readLine());
        Integer[] svc = new Integer[]{0,0,0}; // 최대 3개의 svc 가 주어진다

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            svc[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(svc, Collections.reverseOrder());

        int res = recur(svc[0],svc[1], svc[2]);

        System.out.println(res);
    }

    // 체력 1, 2, 3 ( 체력 내림차순 )과 , 파괴되지 않고 남아있는 svc 의 개수를 인자로 전달
    // 남아있는 개수 별로 서로 다르게 하지 말고, 그냥 모든 경우 확인해 보면... 음수까지 다 생각해야하는데;;
    // 경우를 다 구분해야 하나??
    // 알아서 0이하인지도 검수해야하는 듯 ㅜ-ㅜ
    private int recur(int one, int two, int three) {
        // 셋 다 파괴 된 경우
        if(one <= 0 && two <= 0 && three <=0 ) {
            return 0;
        }
        // 이미 지나간 상황인 경우
        if(dp[one][two][three] != 0 ) return dp[one][two][three];

        int min = Integer.MAX_VALUE;

        Integer[] temp = null;
        // 9, 3, 1
        temp = calc(one - 9, two - 3, three - 1);
        min = Math.min(min, recur(temp[0],temp[1],temp[2]));

        // 9, 1, 3
        temp = calc(one - 9, two - 1, three - 3);
        min = Math.min(min, recur(temp[0],temp[1],temp[2]));
        // 3, 9, 1
        temp = calc(one - 3, two - 9, three - 1);
        min = Math.min(min, recur(temp[0],temp[1],temp[2]));
        // 3, 1, 9
        temp = calc(one - 3, two - 1, three - 9);
        min = Math.min(min, recur(temp[0],temp[1],temp[2]));
        // 1, 3, 9
        temp = calc(one - 1, two - 3, three - 9);
        min = Math.min(min, recur(temp[0],temp[1],temp[2]));
        // 1, 9, 3
        temp = calc(one - 1, two - 9, three - 3);
        min = Math.min(min, recur(temp[0],temp[1],temp[2]));

        return dp[one][two][three]  = min + 1;
    }

    // 항상, one, two, three 에는 가장 큰 체력, 중간 체력, 적은 체력 으로 전달하고 싶음. 그리고 one, two, three 는 항상 0 이상이 되도록 하고 싶다
    // 따라서 전달된 one, two, three 에 대해 9, 3, 1 공격 3,9,1 공격이 가해지더라도,
    // 그 결과 남은 체력을 0 이상의 정수이면서, 내림차순이도록 해 주는 함수
    // 리턴된 결과 배열은 항상 0,1,2 index 에 가장큰,중간,가장작은 수가 들어있게 된다 
    private Integer[] calc(int one, int two, int three) {
        Integer[] res = new Integer[]{one <= 0 ? 0 : one, two <= 0 ? 0 : two, three <= 0 ? 0 : three};
        Arrays.sort(res, Collections.reverseOrder());

        return res;
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
