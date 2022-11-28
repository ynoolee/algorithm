import java.util.*;

class Solution {
    // 처음에 DP 로 생각했는데 그렇게 생각하고 푸니까 이상해짐
    /*
    모르겠는 부분?
    - 1. 이미 방문한 곳을 방문하지 못하게 한 채로 dfs 로 가게 되면, 정답이 되는 경우를 방문하지 못하게 된다 
    - 2. (0,0) 에서 시작하게 해야할지, (1,0) 에서 시작하게 해야할지 (0,1) 에서 시작하게 해야할지?
    - 3. 근데 어떻게 하던 1번의 경우가 생길 수 밖에 없을 것 같음
    
    모르겠다~ discussion 을 보자 
    
    - A -> B 로 가는 최단 경로 문제처럼 생각하고, 디익스트라로 풀라고 한다 
    - shortest path 에 대한 기준을 변경한 디익스트라로 푸는 것이다 
    - 기존의 Dikstra 의 경우 "최소 비용 테이블" 을 두는데, 여기서 "최소 비용" 은 "해당 노드까지의 루트들 중, MAX ABS DIFF" 가 최소인 값으로 하는 것이다 
    */
    
    private final int MAX = Integer.MAX_VALUE;
    
    private int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    
    private int[][] cost; // (0,0) --> (r,c) 로 가는 루트 중, MAX Consecutive cell 사이의 ABS DIFF 가 최소인 값 업데이트
    
    // 어차피 해당 노드로의 루트 중, max 인 애가 있다면 그 이하로는 안 내려가는 거니까 

    private boolean[][] visit; 
    
    
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length; int col = heights[0].length;
        
        cost = new int[row][col];
        // visit = new boolean[row][col]; // dikstra 에서는 visit 이 필요하지 않다
        
        
        // 1 x 1 인 경우에는 0 을 리턴하면 된다. 따라서 따로 처리 할 필요 없다 
        for(int r = 0; r < row; r++) {
            Arrays.fill(cost[r], MAX);
        }
        
        dikstra(heights, row, col);
        
        return cost[row-1][col-1];
    }
    
    
    private void dikstra(int[][] h, int row, int col) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1 , int[] o2) -> o1[2] - o2[2] ); // r, c, max cost
        // visit[0][0] = true; 
        cost[0][0] = 0;
        pq.add(new int[]{0,0,0});
        
        while(!pq.isEmpty()) {
            
            int[] cur = pq.poll();
            
            // 최소 비용 테이블 값보다 큰 값이면 continue
            if(cur[2] > cost[cur[0]][cur[1]]) {
                continue;
            }
            
            if(cur[0] == row -1 && cur[1] == col -1) break;
            
            // 이제 이 노드를 지나는 "루트" 로 , 인접 노드들을 방문하는 경우에는 어떻게 되는지 모두 방문해 보자 
            for(int[] dir : dirs) {
                int nr = cur[0] + dir[0];
                int nc = cur[1] + dir[1];
                
                // out of range
                if(nr >= row || nc >= col || nr < 0 || nc < 0) continue;
                
                // diff b/w cur and next
                int diff = Math.abs(h[cur[0]][cur[1]] - h[nr][nc]); 
                
                // 현재 노드를 지나가는 경우니까, 현재노드 까지의 루트에서의 MAX ABS DIFF 인 cur[2] 와, diff 를 비교해서 MAX 가, 다음 노드까지의 루트에서의 MAX ABS DIFF
                diff = Math.max(diff, cur[2]);
                
                // 이게 cost[nr][nc] 보다 작은 값이라면 업데이트한다
                if(cost[nr][nc] > diff) {
                    cost[nr][nc] = diff; 
                    pq.add(new int[]{nr,nc,diff});
                }
            }
            
        }
        
        
    }

    

}