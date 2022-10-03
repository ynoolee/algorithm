class Solution {
    private int len = 0;
    private int[][] dp = new int[201][20001];
    private int[] gnums;
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        
        len = nums.length;
        gnums = nums;

        for(int n : nums) sum += n;
        
        if(sum % 2 != 0) return false; // 홀수인 경우는 당연히 안된다
        
        return recur(0,0, sum / 2) > 0;
    }
    
    private int recur(int sum, int i, int target) {
        if(i == len) return -1;
        if(dp[i][sum] != 0) return dp[i][sum];
        if(sum > target) return -1;
        if(sum == target) return 1;
        
        return dp[i][sum] = Math.max(recur(sum + gnums[i] , i + 1, target),recur(sum, i + 1, target));
        
    }
}