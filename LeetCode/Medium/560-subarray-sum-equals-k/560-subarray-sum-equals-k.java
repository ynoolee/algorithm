class Solution {
    private int cnt = 0;
    public int subarraySum(int[] nums, int k) {
        // 합이 k 인 subarray(-> 연속한 원소들로 이루어져있음) 의 개수를 리턴하라
        return usingAccumAndMap(nums, k);
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
    
    // using hashmap
    
    private int usingAccumAndMap(int[] nums, int k) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        
        for(int i = 0; i < nums.length; i++) sum[i + 1] = sum[i] + nums[i];
        
        for(int end = 0; end <= nums.length; end++) {
            // sum[end] - k 인 sum[start] 가 map 에 존재하는가? -> 존재한다면 그 개수만큼 count 횟수를 증가시키면 된다 -> 정확한 start 를 알 수는 없지만, map 에 저장된 sum[start] 의 발생횟수들은 모두 end 이전의 index 들에 해당한다 
            if(map.containsKey(sum[end] - k)) {
                cnt += map.get(sum[end] - k);   
            }
            
            map.put(sum[end], map.getOrDefault(sum[end] ,0) + 1);
        }
        
        return cnt;
    }
}