import java.util.*;

class Solution {
    /*
    2조각으로 나누는 것이기 때문에 윈도우사이즈 늘리는 것 처럼 해보려고 함
    select / remain 각각의 자료구조를 관리하고자 한다 
    공평하게 나누는 방법의 가짓수를 구해야하니까, 윈도우 사이즈를 늘리는 것처럼 하면 O(n) 시간이 걸릴 것으로 예상한다
    
    - 각각을 Map 으로 관리한다면, 0이 되는 순간 Map 에서 제거하는 과정도 필요하다
    - 일단 이렇게 풀어보자 
    */
    private Map<Integer,Integer> remain = new HashMap<>();
    private Map<Integer,Integer> select = new HashMap<>();
    
    public int solution(int[] topping) {
        int answer = 0; // 경우의 수를 카운팅 한다 
        
        // 먼저 remain 에다가 채워넣는다
        for(int t : topping) {
            addTo(remain, t);
        }
        
        // System.out.println(String.format("Firstly reamian Size %d", remain.size()));

        
        // 0 ~ (i-1) 까지가 동생의 영역이라고 한다면
        for(int i = 1; i < topping.length; i++) {
            addTo(select, topping[i - 1]);
            deleteFrom(remain,topping[i - 1]);
            
            // System.out.println(String.format("slice At %d - %d", i-1,i));

            if(isEqual()) {
                answer++;
            }
        }
        
        return answer;
    
    }
    
    // map 에 t 를 추가한다 
    private void addTo(Map<Integer,Integer> map, int t) {
        int numb = map.getOrDefault(t,0) + 1;
        map.put(t, numb);
    }
    // map 에서 t 를 뺀다. 0이되면 제거한다
    
    private void deleteFrom(Map<Integer,Integer> map, int t) {
        if(map.containsKey(t)) {
            int numb = map.get(t);
            if(numb == 1) map.remove(t);
            else map.put(t, numb - 1);
        }
    }
    
    // 현재 두 맵의 key 의 가짓수가 동일한가?
    private boolean isEqual() {
        return remain.size() == select.size();
    }
    
}