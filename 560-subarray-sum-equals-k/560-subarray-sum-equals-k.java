class Solution {
    private int cnt = 0;
    public int subarraySum(int[] nums, int k) {
        // 합이 k 인 subarray(-> 연속한 원소들로 이루어져있음) 의 개수를 리턴하라
        return brute(nums, k);
    }
    
    // brute force 로 문제 풀기  
    private int brute(int[] nums, int k ) {
        int len = nums.length;
        int cnt = 0;
        
        // contiguos -> 
        for(int i = 0; i < len; i++ ) {
            int sum = nums[i];
            
            if(sum == k) cnt++;
            
            for(int j = i + 1; j < len; j++) {
                sum += nums[j];
                
                if(sum == k) {
                    cnt++;
                 }
            }
        }
        
        return cnt;
    }
    
    // dynamic programming 
    private void dp(int[] nums, int k) {
        
    }
}