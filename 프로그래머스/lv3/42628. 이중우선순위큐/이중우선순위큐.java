import java.util.*;

class Solution {
    private PriorityQueue<Integer> maxH = new PriorityQueue<>((Integer o1, Integer o2) -> o2 - o1);

    private PriorityQueue<Integer> minH = new PriorityQueue<>((Integer o1, Integer o2) -> o1 - o2);

    private Map<Integer, Integer> map = new HashMap<>();

    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        for(String op : operations) {
            run(op);
        }

        answer[0] = remove(maxH);

        answer[1] = remove(minH);

        return answer;
    }

    private void run(String str) {
        String[] strings = str.split(" ");

        if(strings[0].equals("D")) {
            if(strings[1].equals("-1")) {
                min();
            } else {
                max();
            }
        } else {
            add(Integer.parseInt(strings[1]));
        }
    }
    
    private void add(int n) {
        Integer mappedNumb = map.getOrDefault(n, 0);
        map.put(n, mappedNumb + 1);
        maxH.add(n);
        minH.add(n);
    }

    private void max() {
        remove(maxH);
    }

    private void min() {
        remove(minH);
    }

    // return 삭제당한 원소
    private int remove(PriorityQueue<Integer> pq) {
        while(!pq.isEmpty()) {
            Integer polled = pq.poll();
            Integer mappedNumb = 0;
            if(( mappedNumb  = map.get(polled) ) == 0) continue;
            else {
                map.put(polled, mappedNumb - 1);

                return polled;
            }
        }

        return 0;
    }
}