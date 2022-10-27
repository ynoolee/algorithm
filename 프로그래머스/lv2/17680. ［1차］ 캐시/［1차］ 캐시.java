import java.util.*;

class Solution {
    // LRU -> 최근으로부터 가장 먼 시간에 접근된 것을 우선으로 evict 하는 방식이다 (LFU 는 최근 가장 적게 사용된 것을 우선으로 evict 하는 방식이었던 것으로 기억)

    /**
     * 1. 개수를 따로 관리 2. 개수가 캐시사이즈와 같은 경우 2-1. 현재 접근하려는 문자열이 이미 캐시에 들어있는 경우 (들어있는 애 빼는건 대부분 자료구조에서
     * O(n) 이 걸릴 것 같다) => Map 을 통해, 해당 값에 대한 "최신 접근시간" 을 기록 한다 => evict 되어있다면 -1 , 그게 아니면 "최신 접근시간"
     * 으로 업데이트 해 둔다 2-2. 현재 접근하려는 문자열이 캐시에 들어있지 않은 경우 (evict 해야함) => PQ 에서 Map 에 값이 -1 이 아니고, Map 에
     * n 인데 얘는 m 인 값인 애가 아닐 때 까지 다 poll 하고..... -> 그래도 최대 10만 정도 일 듯
     */
    public class City {

        private String name;

        private int time;

        public City(String name, int time) {
            this.name = name;
            this.time = time;
        }
    }

    public int solution(int cacheSize, String[] cities) {
        Deque<City> q = new ArrayDeque<>();
        Map<String, Integer> map = new HashMap<>();
        int total = 0; // 총 시간 측정
        int size = 0; // 캐시에 들어있는 실제 데이터 개수 카운트
        int time = 0; // 현재 시간

        if(cacheSize == 0) {
            return cities.length * 5;
        }
        for(String c : cities) {
            c = c.toLowerCase();

            // Cache Miss
            if(map.getOrDefault(c,-1) == -1 ) {
                // Full 일 때
                if(size >= cacheSize) {
                    evict(q,map);
                } else {
                    // Not Full 일 때
                    size++;
                }
                total += 5;
            } 
            // Cache Hit
            else {
                // Not Full 일 때 hit 이면 size 증가시키면 안됨 !!!
                total++;
            }
            q.add(new City(c,time));
            map.put(c, time++);
        }

        return total;
    }

    private void evict(Deque<City> q, Map<String, Integer> map) {
        while(!q.isEmpty()) {
            City polled = q.poll();
            // map 상의 값과 같은 값을 가진 원소를 추출하는 것이 "실제 캐시에 들어있던 원소를 추출" 하는 것이다
            if(map.getOrDefault(polled.name, -1) == polled.time) {
                // 캐시에 존재하지 않는 값이라는 의미에서 -1 으로 업데이트한다
                map.put(polled.name, -1);
                break;
            }
        }
    }
    
    
}