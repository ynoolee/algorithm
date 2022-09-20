#include <iostream>

using namespace std; 

int dp[1000][3];
int n;
int my_min(int a, int b)
{
    if(a>b) return b;
    else return a; 
}
int main() {
    int result; 
    cin>>n;
    
    cin>>dp[0][0]>>dp[0][1]>>dp[0][2];
    
    for(int i=1;i<n;i++){
        int rgb[3];
        cin>>rgb[0]>>rgb[1]>>rgb[2];
        
        dp[i][0] = my_min(dp[i-1][1],dp[i-1][2])+rgb[0];
        dp[i][1] = my_min(dp[i-1][0],dp[i-1][2])+rgb[1];
        dp[i][2] = my_min(dp[i-1][0],dp[i-1][1])+rgb[2];
    }
    result  = my_min(dp[n-1][0],dp[n-1][1]);
    result = my_min(dp[n-1][2],result);
    cout<<result;
}