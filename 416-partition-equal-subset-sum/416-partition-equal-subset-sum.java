class Solution {
    private boolean[][] dp = new boolean[201][20001];
    private int[] gnums;
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        
        gnums = nums;

        for(int n : nums) sum += n;
        
        if(sum % 2 != 0) return false; // 홀수인 경우는 당연히 안된다
        
        bottomUp(nums, sum / 2);
        
        return dp[nums.length - 1][sum / 2];
    }
    
    
    /* 
        discussion 에 따르면 0/1 배낭문제라고 한다. 
        왜냐하면, nums[i] 라는 수는 선택하지않을수도/선택할수도 있는 것 이기 때문이다. 
        
         -> boolean[][] dp 를 선언한다 .
        dp[i][j] 는 0 ~ i 번째 까지 의 원소들에서 sum 이 j 인 것을 구할 수 있으면 true, 구할 수 없으면 false 를 저장한다.
        
        dp[0][0] 은 true 이다.
        i 번째 수를 선택하지 않는다면, 사실 dp[i][j] 는 dp[i-1][j] 와 같은 값을 갖는 것이나 마찬가지다. 
        i 번째 수를 선택했다면, dp[i][j] 는 dp[i-1][j - nums[i]] 와 같은 값을 갖는 것이나 마찬가지다. 
        
        우리는 i 번째 수를 선택할 수도, 선택하지 않을 수도 있기 때문에, dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]] 인 것이다. 
        둘 중 하나라도 true 이면 dp[i][j] 는 true 가 된다. 
        
        우리는 i 번째 까지 원소들을 사용해서 sum/2 가 나올 수 있는지를 알아봐야 하는 것이기 때문에 j 의 범위는 sum/2 이하로 잡는다.
    */
    private void bottomUp(int[] nums, int target) {
        
        for(int i = 0; i < nums.length; i++) {
            // i 번째 까지 아무것도 선택 안한 경우들에 대한 초기화
            dp[i][0] = true; 
            // i 번째 원소만을 선택한 경우들에 대한 초기화
            dp[i][nums[i]] = true; 
        }
        
        
        for( int i = 1; i < nums.length; i++ ) {
            for(int j = 0; j < nums[i]; j++) {
                dp[i][j] = dp[i-1][j];
            }
            for(int j = nums[i]; j <= target; j++) {
                dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]]; 
            }
        }
        
    }
}