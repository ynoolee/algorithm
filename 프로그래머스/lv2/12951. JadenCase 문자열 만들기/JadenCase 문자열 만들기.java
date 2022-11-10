import java.util.*;

class Solution {
    /*
    첫 문자 대문자. 나머진 소문자
    첫 문자가 알파벳이 아니어도, 다음 문자가 모두 소문자이면 된다.
    s 가 주어졌을 때 JadenCase 로 변환하라
    
    ## 정답 만들기 
    - 이 문제는 그냥 순차적으로 문자열에 문자를 더해주기만 해도 될 듯 하다 
    - StringBuilder 만 써줘도 괜찮을 듯 
    
    ## 단어 추출
    "a   b" 인 경우 split 을 사용하면 a, , , ,b 가 나온다 
    반면 StringTokenizer 로는 (1개 이상의)띄어쓰기로 구분된 두 단어를 구분하여 추출 가능하다 
    ## 규칙
    - 첫 문자 
    ----영어 -> 대문자
    ----else -> 그대로
    - 외의 문자 
    --> 소문자
    
    ## 틀렸던 이유
    'a   b' -> 'A B' 로 변환하려고 하고 있었당.. 'A  B'로 변환해야함
    또한 문장의 마지막에 공백이 있는 경우도 고려해 주어야 한다 
    따라서 나는 char[] 형태로 문자열을 관리할 수 있는 StringBuilder 로 풀기로 함
    */
    public String solution(String s) {
        
        StringBuilder sb = new StringBuilder(s);
        StringTokenizer st = new StringTokenizer(s);
        
        // 다음 단어가 있을 때 까지 
        int idx = -1;
        while((idx = findWord(sb, idx)) != -1) {
            idx = convert(sb, idx);
        }
        
        return sb.toString();
    }
    
    // idx 이후에서부터, 다음 단어를 찾는다.
    // 없는 경우 return -1
    private int findWord(StringBuilder sb, int idx) {
        for(int i = idx + 1; i < sb.length(); i++) {
            if(sb.charAt(i) != ' ') return i;
        }
        return -1;
    }
    
    // 현재 변환하려는 단어의 시작 인덱스 -> start
    // return : 단어 다음 글자 index 
    public int convert(StringBuilder sb, int start) {
        int cur = start;
        char c = sb.charAt(cur);
        
        
        while(cur < sb.length()) {
            c = sb.charAt(cur);
            
            if(c == ' ') break;
            
            if(cur == start) {
                // 단어의 첫 글자가 소문자 알파벳인 경우만, 변환 해 준다 (대문자로)
                if(c >= 'a' && c <= 'z') {
                    sb.setCharAt(cur, (char)(c - 'a' + 'A'));
                } 
            } else {
                // 단어의 첫 글자가 아닌 경우 -> 대문자인 경우만 소문자로 변환
                if(c >= 'A' && c <= 'Z') {
                    sb.setCharAt(cur, (char)(c - 'A' + 'a'));
                }
            }
            cur++;
        }
        
        // 띄어쓰기인 경우 loop 를 벗어난다
        return cur;
    }
}