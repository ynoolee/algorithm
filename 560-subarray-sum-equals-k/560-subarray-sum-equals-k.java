class Solution {
    private int cnt = 0;
    public int subarraySum(int[] nums, int k) {
        // 합이 k 인 subarray(-> 연속한 원소들로 이루어져있음) 의 개수를 리턴하라
        return accum(nums,k);
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
    
    // accumulated sum  
    private int accum(int[] nums, int k) {
        // to make sum, time complexity O(n) - sum[i + 1 ] = sum[i]  + nums[i + 1] -> to make it easier, init sum[0] = 0,and make sum array as array which size is nums.length + 1
        int cnt = 0;
        
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        
        for(int i = 0; i < nums.length; i++) sum[i + 1] = sum[i] + nums[i];
        
        // sum of (i, j)  -> sum[j]  - sum[i] 
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j <= nums.length; j++) {
                if(sum[j] - sum[i] == k) cnt++;
            }
        }
        
        return cnt;
        
    }
}