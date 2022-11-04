import java.util.*;
class Solution {
    int MOD = 20170805;

    /*
    이 문제는 이동하는 방향이 두 가지 경우 밖에 없다 : ➡️, ⬇️
    ➡️ 의 우회전 -> ⬇️
    ⬇️ 의 좌회전 -> ➡️
    어차피 여기서는 "그대로 or 우회전이나좌회전" 의 경우 밖에 없음
    
    그러니 dirs[][] 에는 아래로 내려가기, 오른쪽으로 이동 하기 만을 담아두고
    rotate 에는 현재 방향에서 방향을 틀었을 때의 다음 방향을 담는다
    
    이런 문제는 분명 DP 임이 틀림없다. 이때 겹치게 되는 subproblem 의 형태는 "[r][c] 에 dir 방향으로 접근하는 것" 이다.
    
    그런데 시간 초과가 발생했다..?? 모지 --> dp 를 -1 로 초기화하고, -1이 아닌 경우 이미 해결한 subproblem 인 것으로 변경하니 풀렸다
    
    0으로 초기화 해도 상관없을거라 생각했는데 아닌가보다. 그 이유가 뭘까...??? 
    
    아하, 나는 map[r][c] 가 1인 경우만 바로 리턴하게 하면, 쓸데 없는 경우들까지 파고들지 않을거라 생각했다.
    그런데 , map[r][c] 로'만' 가는 경로를 가진 preR,preC 는 사실상 다음 칸으로 이동 해 볼 필요가 없다. 그런데 이 칸들은 항상 0 이라는 값을 가질 것이다. 그리고 내가 dp 를 0으로 초기화 하고 있을 경우에는 이 경우들은 아직 방문하지 않은 값으로 처리된다.    따라서 반복된 subproblem 인데 매 번 파고들어가게 된다. 
    
    이 문제는 0 이 아닌 다른 초기값을 가져야 하는 것이었다.
    */
    private int[][][] dp = new int[501][501][2]; // dp[r][c] 에 들어올 때의 방향에 따라 , 다음에 이동할 수 있는 칸들이 달라진다 -> 따라서 3차원 배열

    private int[][] dirs = new int[][]{{1, 0}, {0, 1}}; // 아래로 내려가기, 오른쪽으로 가기

    private int[] rotate = new int[]{1, 0}; // index 를 rotate 하면 rotate[index] 방향이 된다

    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n;c++) {
                Arrays.fill(dp[r][c], -1);
            }
        }

        dp[m - 1][n - 1][0] = 1;
        dp[m - 1][n - 1][1] = 1;

        // 문제에서 주어진 것에 의하면 (0,0) 칸은 항상 0 을 갖는다. 따라서 항상 오른쪽, 아래 방향 모두로의 이동을 수행한다
        answer += recur(0, 0, 0, cityMap); // 따라서 방향별로 호출하지 않아도 된다

        answer %= MOD;

        return answer;
    }

    private int recur(int dir, int r, int c, int[][] m) {
        // 범위 확인 , 1 인 경우 지나갈 수 없는 칸
        if (r >= m.length || c >= m[0].length || m[r][c] == 1) {
            return 0;
        }

        if (dp[r][c][dir] != -1) {
            return dp[r][c][dir];
        }

        int cur = m[r][c]; // 현재 칸의 상태
        int sum = 0;

        // 1 인 칸은 애초에 차단 된다
        // 그렇다면 두 경우 모두 "현재 방향 그대로 직진" 은 동일하다
        sum += (recur(dir, r + dirs[dir][0], c + dirs[dir][1], m) % MOD); // 방향 그대로

        if (cur == 0) {
            // 방향을 반대로
            int rotateDir = rotate[dir];
            sum += (recur(rotateDir, r + dirs[rotateDir][0], c + dirs[rotateDir][1], m) % MOD);
        }

        return dp[r][c][dir] = sum % MOD;
    }
}
