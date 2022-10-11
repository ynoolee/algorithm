import java.util.stream.IntStream;

class Solution {
    /**
     * N 개의 수 중에서 소수가 "몇 개 " 인지 찾는 문제
     * 1개의 수가 소수인지를 판별하는 문제가 아니다
     * 이 문제의 경우 N 은 100 만이하의 수이기 때문에 내가 정리한 소수 판별 방법 세 가지 중 naive 한 방식으로는 답 도출 불가능(시간초과))
     *
     * */
    public int solution(int n) {
        int answer = divisor(n);
        return answer;
    }


    // O(n^2)
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

    // O(N루트N)
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

    // O(Nlog(logN)) 따라서 이 방식은 N 이 커도 평균적으로 적은 시간이 걸린다
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