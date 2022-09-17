import java.util.*;
import java.util.Map.Entry;

class Solution {
    // 각 order 의 character 들이 오름차순으로 주어지는 것은 아니다 ! 
    private int[] max = new int[11]; // 각 개수 당 -> max 등장횟수를 저장하는 배열

    private Map<String, Integer> map = new HashMap<>();

    private StringBuilder sb = new StringBuilder("");

    public String[] solution(String[] orders, int[] course) {
        for (int c : course) {
            for (String order : orders) {
                find(order, c, 0, 0);
            }
        }

        List<String> list = new ArrayList<>(orders.length);

        for (Entry<String, Integer> e : map.entrySet()) {
            if (e.getValue() >= 2 && e.getValue() == max[e.getKey().length()]) {
                list.add(e.getKey());
            }
        }
        
        Collections.sort(list);

        return list.toArray(new String[]{});
    }
    
    private void find(String order, int c, int cnt, int cur) {
        if (cnt == c) {
            char[] chars = sb.toString().toCharArray();
            Arrays.sort(chars);
            String str = String.valueOf(chars);

            int numb = map.getOrDefault(str, 0) + 1;
            map.put(str, numb);

            max[c] = Math.max(max[c], numb); // max update
            return;
        }

        for (int i = cur; i <= order.length() - (c - cnt); i++) {
            sb.append(order.charAt(i));
            find(order, c, cnt + 1, i + 1);
            sb.delete(cnt, cnt + 1);
        }
    }
}