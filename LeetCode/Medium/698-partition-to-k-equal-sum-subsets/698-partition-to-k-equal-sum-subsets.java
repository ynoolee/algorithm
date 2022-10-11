class Solution {
    /**
    정수 배열 nums 과 정수 k 가 주어졌을 때, (nums 의 모든 원소는 자연수 이다)
    해당 배열을 , sum 이 동일한 k 개의 subset "들" 로 분리하는 것이 가능한지 여부를 알려주는 프로그램을 작성하라
    */
        
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum  = IntStream.of(nums).sum(); // 새로운 스트림 사용법을 알았다 
        
        if(sum % k != 0) return false;
                
        return backTrack(nums, sum / k, new boolean[nums.length], 0, k, 0);
    }
    
    // 모든 경우에 대해 수행 
    // visit 을 사용하여 아직 사용되지 않은 원소를 사용한다
    // 이 문제를 백트랙킹으로 풀려면, 무한 반복을 막을 방법을 생각해야 할 듯 하당 
    // 현재 만들고 있는 subset 의 누적값, 현재 만들고 있는 subset 
    // target 과 같은 sum 을 가진 subset 을 하나 구하면 -> cnt 를 증가시키고, idx = 0 부터 다시 루프를 돌면서 또 다른 subset 을 찾아야 한다 
    // 근데 여기서 계속 진전이 안 나간당. k 개의 subset 을 만들면서 무한루프 안 도는 방법은 무엇..?? discussion 을 보았다 
    /*
        discussion 방식은, 모든 관련 값을 인자로 전달 하는 방식이다. 
        하나의 subset 을 생성하면 -> 만을어야 하는 개수 k 를 1 감소시키고, 다음 subset 을 생성해야 하니, visit 테이블은 그대로 두면서 index 0 부터 다시 iterate 한다. 
        
    */
    private boolean backTrack(int[] nums, int target, boolean[] visit, int curSum, int k, int start) {
        if( k == 1 || k == 0) return true; 
        if(curSum == target) {
            return backTrack(nums, target, visit, 0, k - 1 , 0); // 1개의 서브셋을 찾았으니, k-1 로 줄이고, 다시 index 0 부터 루프를 돌면서 찾아보라는 것
        }
        
        for(int i = start; i < nums.length; i++) {
            if(visit[i]) continue;
            // 시간초과가 나기 때문에, 일부 subproblem 들은 건너뛰어야 하기 때문에 아래 로직을 추가한다.
            if(i > 0 && nums[i - 1] == nums[i] && !visit[i-1]) continue;
            if(curSum + nums[i] > target) continue;
            visit[i] = true;             
            if ( backTrack(nums, target, visit, curSum + nums[i] , k, i + 1) ) return true; 
            visit[i] = false;
        }
        
        // for 문을 끝까지 돌았는데, true 로 리턴하지 못했다면, 실패죠
        return false;
    }
    
}