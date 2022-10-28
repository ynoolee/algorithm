import java.util.*;

class Solution {
    /**
    실제로 팰린드롬을 만들지 않아도 됨. 
    그리고 주어진 문자열에서 "연속된 문자열" 이 아니라! 그냥 글자들 뽑아서 만들 수 있는 팰린드롬 중 가장 긴 팰린드롬의 길이를 구하는 문제일 뿐이다.
    이것도 Map 사용해서 스트림 필터링 하면서 풀 수 있을 듯 하다. 
    
    case sensitive 대소문자를 구분한다. 
    
    그냥 글자별 개수를 세어보고, 
    짝수개 존재하는 것들 전부 + 홀수개 존재하는 글자가 존재하면 짝수개 만큼만 뽑아서 쓰면됨.
    
    즉, aaa 이더라도 aa 만 뽑아쓰면 팰린드롬을 만들 수가 있다.
    
    --> 이 문제는 "짝" 이 만들어질 때 마다 1을 카운트 하면 된다 
    --> 그리고, 홀 수개 존재하는 글자가 1개 이상 존재한다면 -> 1을 더해 주면 된다. 
    
    이를 위해서 HashSet 을 사용한다면? 
    
    - 글자를 순회하면서
    --- Set 에 존재하는 글자면, Set 에서 해당 글자를 제거 && 카운트 + 2
    --- Set 에 존재하지 않는 글자라면, Set 에 추가 한다 
    - 마지막에는 , Set 이 비어있지 않을 경우 , 카운트 + 1 
    
    */
    public int longestPalindrome(String s) {

        // 글자 별 개수를 초기화
        Set<Character> set = new HashSet<>();
        int cnt = 0;
        
        for(int idx = 0; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            
            if(set.contains(c)) {
                set.remove(c);
                cnt+= 2;
            }else {
                set.add(c);
            }
        }

        if(!set.isEmpty()) cnt++;
        
        return cnt;
    }



}