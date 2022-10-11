class Solution {
    private boolean[][] dp = new boolean[201][20001];// 2천만 -> boolean cache 를 쓰고 싶은데 , 이 경우 bottom up 을 해야 한다. top down 의 경우, dp 는 이미 false 인 경우도 패쓰 해 버릴 수 있음 
    private int[] gnums;
    
    
    // 20221008 다시 풀기 -> 양의 정수들을 갖는 nums 가 주어졌을 때, 원소들의 합이 같은 두 개의 subset 으로 나눌 수 있는지 여부를 리턴하라
public boolean canPartition(int[] nums) {
    int sum = IntStream.of(nums).sum();
        
        if(sum % 2 != 0 )return false;  // 2 개의 subset 으로 나누고 , 각각의 subset 의 합이 같아지는 것을 기대하는 것 이기 때문에, sum 이 홀수라면 불가능 하다
        
        return dp(nums, sum/2);
        
    }

    // i 번째 까지 sum 이 j 인 subset 을 만들 수 있는지 여부를 cache[i][j] 에 저장한다.
    // target : subset sum 이 target 인 subset 을 만들 수 있는지 여부를 리턴한다
    private boolean dp(int[] nums, int target) {
        // i 번째 수를 선택 할 수도, 선택하지 않을 수도 있다
        for(int i = 0; i < nums.length; i++) {
            // i 번째 까지 아무것도 선택하지 않은 경우
            dp[i][0] = true;
             // j 번째 까지 선택한 수가 i 번째 뿐인 경우 
            for(int j = i; j < nums.length; j++) {
                dp[j][nums[i]] = true; 
            }
        }
        
        // dp[i][j] 는 dp[i-1][j] 가 true 이거나 dp[i-1][j-nums[i]] 가 true 였던 경우 true 이다
        // j > target 인 경우와, j < nums[i] 인 경우에 대해서는 여기 for 문에서는 돌 필요 없다. 위에서 세팅해줌
        for(int i = 1; i < nums.length; i++) {
            for(int j = nums[i] + 1; j <= target; j++) {
                dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
            }
        }
        
        return dp[nums.length - 1][target];
    }
    
    

    
    
    
    
    
    public boolean canPartition1(int[] nums) {
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
        ≠
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