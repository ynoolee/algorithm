class Solution {
    /**
    nums 라는 정수 배열이 주어졌을 때, 
    가장 최대의 곱을 만들어내는 non-empty contiguous subarray 를 찾아서, 그 곱을 리턴하라
    - contiguous
    - product
    
    이 문제는 곱을 구하는 것이기 때문에, 음수가 짝수번 나왔는지, 홀수 번 나왔었는지도 중요하다.
    
    
    cumulative product 인 이 문제는 어떻게 해야 할까? 
    - 일단 곱셈의 특징은 --> 가장 작던 값이 한 순간에 가장 큰 값이 될 수 있다는 것이다.( 음수값을 곱하는 상황 )
    - 따라서 이 문제에서는, 현재까지의 subarray 에서의 최대, 최소값을 추적해야 한다
    - 그리고 이제까지의 max 는 따로 추적해야 함 ㅇㅇ
    
    cumulative sum 이었다면 분명 이런 과정이 있었을 것이다 -> sum[i] < arr[i] 라면 i 부터 시작하는 서브 array 를 만든다( 그와 별개로 Max 는 계속해서 기록한다 )
        
    여기서도 이와 유사하게 
    - 현재까지의 subarray 에서의 최대 와, arr[i] 를 비교해서 더 큰 값을 최대로 세팅한다
    - 현재까지의 subarray 에서의 최소 와, arr[i] 를 비교해서 더 작은 값을 최소로 세팅한다
    
    그리고, 현재 값이 "음수" 인 경우, 지금까지의 최대,최소를 swapping 시키는 결과가 발생할 수도 있

    */
    public int maxProduct(int[] nums) {
        int curMax = nums[0]; int curMin = nums[0];
        int max = curMax;
        
        for(int i = 1; i < nums.length; i++) {
            // 현재 원소가 음수인 경우 -> 현재 수를 곱할 경우, 최대, 최소가 서로 뒤바뀌게 된다
            if(nums[i] < 0) {
                int temp = curMax; 
                curMax = curMin;
                curMin = temp;
            }
            
            curMax = Math.max(nums[i], curMax * nums[i]); // 현재 수 로 시작하는 subarray 로 바꿔야 하는지
            curMin = Math.min(nums[i], curMin * nums[i]); // 현재 수 로 시작하는 subarray 로 바꿔야 하는지
            
            max = Math.max(max, curMax);
        }
        
        
        return max;
    }
    
}