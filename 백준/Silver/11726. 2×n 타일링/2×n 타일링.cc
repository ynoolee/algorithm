#include <iostream>

using namespace std; 

// idx를 이용위해 1001크기로 배열 선언
// 나머지 연산의 특징상
int dp[1001]; //4000byte-> 4KByte , 초기화 자동으로 0 ? 

int solve_dp(int n)
{
	if (n <= 2) return n;
	else {
		dp[n] = (dp[n - 1] + dp[n - 2]) % 10007;
		return dp[n];
	}
}

int main()
{	
	dp[1] = 1;
	dp[2] = 2;
	int n,test; 
	cin >> n; 
	for (int i = 1; i <= n; i++)
	{
		test= solve_dp(i);
		//printf("%d : %d\n", i, test);
	}
	printf("%d", dp[n]);

}


