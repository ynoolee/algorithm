import java.util.*;
import java.io.*;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private void run() throws Exception {
        int n = Integer.parseInt(br.readLine());

        List<Integer> primes = primes(n);
        int res = twoPointers(primes, n);

        System.out.println(res);
    }

    // 소수인 수들을 찾는다
    public static List<Integer> primes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false; // 0과 1은 소수가 아니므로 false로 설정
        isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            // 2 부터 시작
            if (isPrime[i]) {
                // 2의 배수들은 모두 Prime 이 아니도록 세팅
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    // n 을 만드는 경우의 수
    private int twoPointers(List<Integer> primes, int n) {

        int l = 0;
        int sum = 0;
        int cnt = 0;

        for (int r = 0; r < primes.size(); r++) {
            sum += primes.get(r);


            if (sum > n) {
                // l 이 r 이하일 때 까지 이동
                for (; l <= r; l++) {
                    // sum <= n 이 될 때 까지 l 을 이동 시킨다
                    sum -= primes.get(l);
                    if (sum == n) {
                        cnt++;
                    } else if (sum < n) {
                        l++;
                        break;
                    }
                }
            } else if (sum == n) {
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}