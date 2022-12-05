import java.util.*;
class Solution
{
    /*
    이 문제의 특징 -> 입력값 은 "자연수"
    
    어떻게 곱하더라도 모두 양의 값이다.
    
    이 문제는 최대 x 최소를 서로 곱해서 누적합을 구해야 할 것 같다 (틀리면.. 모든 경우의 수를 다 해보는 거로)
    
    */
    public int solution(int []A, int []B)
    {
        int ans = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i = 0; i < A.length; i++) {
            ans += A[i] * B[B.length -1 - i];
        }

        return ans;
    }
}