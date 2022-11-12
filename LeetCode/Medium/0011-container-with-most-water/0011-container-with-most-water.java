class Solution {
    /*
          .|
    .     .|
    . .   .h
    . . . .|
    ---x---|
    
    이 컨테이너가 담을 수 있는 물의 부피(volume) -> h * x
    - 중요한건 min H 이다 
    
    1) 단순하게 생각하면 -> 모든 경우의 수를 구하는 브루트 포스 느낌의 풀이가 있다. 
    하지만 문제 조건상 n 은 10만이기에, 억을 넘어가는 경우의 수가 나온다 O(n^2) 
    2) 그렇다면 O(nlogn) 이던 O(n) 으로 풀어 내야 한다.
    O(n) 하면 지금의 경우 투 포인터가 떠오른다 
    --- 이 때 문제점 . left, right 를 이동시켜야하는데, "무엇을 기준"으로"누구"를 이동시키지???
    --- 핵심은 min H 다. 결국 min H 를 높여야 한다. ( 두 선분이 2, 20 이더라도, 높이는 2 이기 때문)
    --- 따라서, 높이가 더 작은 쪽을 이동시키도록 해야 한다 
    
    
    */
    public int maxArea(int[] height) {
        int l = 0; int r = height.length - 1; 
        int max = 0;
        
        while(l < r) {
            int temp = (r - l) * Math.min(height[l], height[r]);
            max = Math.max(max, temp);
            
            // move pointer which has shorter height
            if(height[l]< height[r]) l++;
            else r--;
            
        }
        
        return max;
    }
}