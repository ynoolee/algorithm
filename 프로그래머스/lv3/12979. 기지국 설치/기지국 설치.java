class Solution {

    public int solution(int n, int[] stations, int w) {
        int answer = greedy(n, stations, w);

        return answer;
    }
    
    // N 이 2억인데 그리디로 풀어도 괜찮을까? 일단 해 보자 
    private int greedy(int n, int[] stations, int w) {
        int cnt = 0; // 추가할 기지국의 개수 
        int s = 0; // 현재 기준이 되는 기지국
        

        // cur 은, 기지국의 전파가 닿는지 알 수 없는 위치들을 대상으로 하도록 하자
        for(int cur = 1; cur <= n; cur++) {
            // 이미 존재하던 기지국의 전파가 닿는 곳인 경우 -> 다음 cur 은 stations[s] + w + 1 인 위치가 되어야 한다
            if(s < stations.length && stations[s] - w <= cur && stations[s] + w >= cur) {
                cur = stations[s] + w;
                // 주어진 기지국에서 비교 대상인 기지국 또한 다음 기지국으로 변경한다
                s++;
            } else {
                // 현재 전파가 닿지 않는 위치일 경우 -> 최대한 적게 설치하기 위해서는 cur + w 위치에 기지국을 설치해야함 -> 다음 cur 은 cur + 2w + 1 인 위치가 되어야 한다 
                // 주어진 기지국들은 정렬된 위치들로서 주어진다
                cnt++;
                cur += 2 * w;
            }
        }
        
        return cnt;
    }

}