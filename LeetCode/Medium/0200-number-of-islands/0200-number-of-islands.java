import java.util.*;

class Solution {
    /*
    얼마전 코테에서 풀었던 문제와 굉장히 유사. 
    이미 백준이나 프로그래머스에서 풀었던 문제일 거임. 
    섬의 개수 . 
    
    
    - 나는 이 문제를 각각 dfs or bfs 로 풀어서 모든 land 를 방문 할 때 까지 전체 탐색을 할 것 같다
    - 그런데 다른 방식도 있는 것 같은데 그 부분을 좀 더 살펴 봐야 할 것 같다
    */
    
    private int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    
    public int numIslands(char[][] grid) {
        int row = grid.length; int col = grid[0].length;   
        
        // return method1(grid, row, col);
        return unionFind(grid, row, col);
    }
    
    
    private int method1(char[][] grid, int row, int col) {
        int cnt = 0; 
        
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(grid[r][c] == '1') {
                    dfs(r, c, grid, row, col);
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
    

    private void dfs(int r, int c, char[][] g, int row, int col) {
        g[r][c] = 0;
        
        for(int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            // out of range
            if(nr >= row || nc >= col || nr < 0 || nc < 0) continue; 
            if(g[nr][nc] == '1') {
                dfs(nr,nc, g, row, col);
            }
        }            
    }
    
    /*
    이걸 union find 로 풀기도 한다고 한다. 어떻게 유니온 파인드로 문제를 푼 거지..???!
    
    Union find 에서는 보통, 두 노드 사이에 "child-parent" 라는 관계를 만들어 둔다.
    이를테면 노드1,2 사이에서는 숫자가 더 작은 애를 parent node 라고 하는 것이다. 
    이렇게 하는 이유는, a 가 b 의 하위 , b 가 c 의 하위 라는 관계를 도출 할 경우 , a 와 c 는 같은 그래프이며 a 는 c 의 하위 그래프에 속해 있음을 알 수 있기 때문이다. 
    
    @(보통)이러한 관계정보를 통해 "사이클 없는 그래프" 를 만드는 것이 가능하다. 
    - 그 원리는 , 각 노드에 대해 parent node 정보를 업데이트하며, 어떤 노드 a 와 다른 어떤 노드 b 를 이어 나가려 할 때, 이미 a 의 부모와 b 의 부모가 같은 경우 a - b를 연결하면 이는 cycle 이 생성 되는 것이 되기 때문이다. 
    
    @(그런데)이 문제에서는 union find 를 사용하여, 같은 그래프에 속한 노드들을 판단해야 할 것 같다. 
    
    그런데 지금까지는 1D table 로서, parent 정보를 업데이트해가며 풀 수 있었는데
    현재 이거는 2D 로만 노드에 대한 정보가 주어져 있다 그런데 이를 어떻게 해결하지? -> discussion 을 보니 col * r + c 를 통해 이를 나타내기도 했다. 
    
    -- 먼저, 두 노드 사이의 관계를 어떻게 생각해야할까?
    ----- 아니.... 내가 이제까지 풀었던 문제는 보통 , 각 줄 에 2 3 이런식으로 주어지면 2번과 3번 사이에 간선이 있다는 관계를 의미했는데
    ----- 현재 이 문제는 어떻게 해야하는지 감이 잡히지 않았다
    --------- 모든 노드들을 iterate 하면서 '1' 인 노드가 존재할 경우, 해당 노드의 인접노드들을 모두 확인 ->인접노드 중에서도 '1'인 노드가 있을 경우, 두 노드를 union 시킨다 
    
    
    - 아니 근데, 이거로 어떻게 "그래프의 개수" 를 구할 수 있는거지??
    ----- 모든 노드들 간의 관계를 union find 한 이후에도 ,  각 노드는, 자신이 속한 서브그래프 상의 최상위 노드를 parent 로 갖고 있지는 않을 수도 있다.
    ----- 하지만, 같은 그래프에 속해있다면 find(node) 를 호출 했을 때 결국은 같은 parent 를 리턴 받게 된다.
    ----- 이를 이용하는 것이다 -> 결국 가장 최상위 노드의 개수가 섬의개수와 같다 
    
    */
    
    private int unionFind(char[][] g, int row, int col) {
        int[] parent = new int[row * col]; 
        init(parent); // 자기 자신의 index 로 초기화 한다. 
        
        for(int r = 0; r < row ; r++) {
            for(int c = 0; c < col; c++) {
                
                if(g[r][c] == '1') {
                    for(int[] dir : dirs) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        
                        if(nr >= 0 && nc >= 0 && nr < row && nc < col && g[nr][nc] == '1') {
                            union(col * r + c , col * nr + nc, parent);
                        }
                    }
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        // parent[idx] 에서도 .. 결국 '1' 이 아니었던 칸은 다들 "자기자신" 을 갖고 있기 때문에, 그거를 필터링 해 줘야 함
        for(int r = 0 ; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(g[r][c] == '1') set.add(find(col * r + c, parent));
            }
        }

        
        return set.size();
    }
    
    // union 과정에서는 부모를 find 하는 과정이 들어간다 
    private int find(int child, int[] parent) {
        // 자기 자신일 수도 있음
        if(child == parent[child]) return child;
        
        return parent[child] = find(parent[child], parent);
    }

    // 더 작은 애가 parent 가 되도록 한다
    private void union(int n1, int n2, int[] parent) {
        int p1 = find(n1, parent);
        int p2 = find(n2, parent);
        
        if(p1 >= p2) parent[p2] = p1;
        else parent[p1] = p2; 
    }
    
    private void init(int[] parent) {
        for(int i = 0; i < parent.length; i++) {
            parent[i] = i; 
        }
    }
    
    
}