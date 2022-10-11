import java.util.stream.IntStream;

class Solution {
    /**
     * N 개의 수 중에서 소수가 "몇 개 " 인지 찾는 문제
     * 1개의 수가 소수인지를 판별하는 문제가 아니다
     * 이 문제의 경우 N 은 100이하의 수이기 때문에 내가 정리한 소수 판별 방법 세 가지 모두 정답을 도출 할 수 있다.
     *
     * */
    public int solution(int n) {
        int answer = divisor(n);
        return answer;
    }


    // O(n)
    private int naive(int n){
        int cnt = 0;

        for(int i = 2 ; i <= n; i++) {
            boolean flag = false;
            // 각 i 가 소수인지 판별한다
            for(int j = 2; j < i; j++) {
               if(i % j == 0)  {
                   flag = true;
                   break;
               }
            }

            if(!flag) cnt++;
        }

        return cnt;
    }

    // O(루트N)
    private int divisor(int n) {
        int cnt = 0;

        for(int i = 2 ; i <= n; i++) {
            boolean flag = false;
            // 각 i 가 소수인지 판별한다
            for(int j = 2; j <= Math.sqrt(i); j++) {
                if(i % j == 0)  {
                    flag = true;
                    break;
                }
            }

            if(!flag) cnt++;
        }

        return cnt;
    }

    // O(Nlog(logN))
    private int eratos(int n) {
        boolean[] visit = new boolean[n + 1]; // index 가 숫자이기 때문에 n + 1 크기여야함

        for(int i = 2 ; i <= Math.sqrt(n); i++) {
            // i 를 제외한 i의 배수를 모두 방문 처리 한다
            int multiplier = 2;
            while(i * multiplier <= n) {
                visit[i * multiplier] = true;

                multiplier++;
            }
        }


        return (int)IntStream.range(2, n + 1)
            .filter(idx -> !visit[idx])
            .count();
    }
}