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
        
        return method1(grid, row, col);
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
    
    
}