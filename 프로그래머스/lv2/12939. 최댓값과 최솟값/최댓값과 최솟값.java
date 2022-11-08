import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        StringTokenizer st = new StringTokenizer(s);
        
        while(st.hasMoreElements()) {
            String cur = st.nextToken();
            max = Math.max(max, Integer.parseInt(cur));
            min = Math.min(min, Integer.parseInt(cur));
        }
        
        answer += Integer.toString(min) +" "+ Integer.toString(max);
        
        return answer;
    }
}