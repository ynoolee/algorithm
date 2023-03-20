class Solution {
    public int solution(int n, int k) {
        int answer = -1;

        String number = convert(n, k);
        answer = findPrime(number);

        System.out.println(answer);
        return answer;
    }

    private int findPrime(String str) {
        int cnt = 0;
        int len = str.length();

        StringBuffer buffer = new StringBuffer();

        for(int idx = 0; idx < len; idx++) {
            // 현재 가 0 인 경우
            if(str.charAt(idx) == '0') continue;

            buffer.append(str.charAt(idx));
            // 마지막 글자인 경우
            if(idx == len - 1 ) {
                long numb = Long.parseLong(buffer.toString());
                if(isPrime(numb)) cnt++;
                buffer = new StringBuffer();
            } else {
                if(str.charAt(idx + 1) == '0') {
                    // 다음 글자가 0인 경우
                    long numb = Long.parseLong(buffer.toString());
                    if(isPrime(numb)) cnt++;
                    buffer = new StringBuffer();
                }
            }
        }

        return cnt;
    }


    private boolean isPrime(long n) {
        long root = (long)Math.sqrt(n);

        if ( n == 1 ) return false;

        for(long i = 2; i <= root; i++) {
            if(n % i == 0) return false;
        }

        return true;
    }

    private String convert(int n, int k) {
        StringBuffer buffer = new StringBuffer("");

        int next = 0;
        int cur = n;

        while((next = cur/k) != 0) {
            int numb = cur % k;
            buffer.append(numb);
            cur = next;
        }

        int last = cur % k;
        buffer.append(last);

        buffer.reverse();

        return buffer.toString();
    }

}