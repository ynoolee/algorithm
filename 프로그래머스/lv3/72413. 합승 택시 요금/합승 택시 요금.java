import java.util.*;
class Solution {
    private static final int INF = 200_000_00;
    // n : 노드 개수
    // s : 시작점
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] table = init(fares, n);
        floyd(table, n);

        int min = Integer.MAX_VALUE;
        for(int x = 0; x < n; x++) {
            int na = a-1; int nb = b-1; int ns = s-1;
            // sa + sb 구해 두고, sx + xa + xb 중 최소가 되는 것 구하기
            int temp = Math.min(
                table[ns][na] + table[ns][nb],
                table[ns][x] + table[na][x] + table[nb][x]);
            min = Math.min(temp, min);
        }

        return min;
    }

    // 각 노드들 사이의 최대 경로를 구하여 세팅한다
    private void floyd(int[][] table, int n) {
        for(int c = 0; c < n; c++) {
            // 모든 ij 에 대해 ic + jc 가 ij
            for (int i = 0; i < n; i++) {
                if(c == i) continue;
                for (int j = 0; j < n; j++) {
                    if( i == j || c == j) continue;
                    if(table[i][j] > table[i][c] + table[j][c]) {
                        table[i][j]  = table[i][c] + table[j][c];
                        table[j][i]  = table[i][c] + table[j][c];
                    }
                }
            }
        }
    }

    private int[][] init(int[][] fares, int n) {
        int[][] table = new int[n][n];

        for(int[] f : fares) {
            // f[0] 과 f[1] 사이의 비용 f[2]
            table[f[0]-1][f[1]-1] = f[2];
            table[f[1]-1][f[0]-1] = f[2];
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) table[i][j] = 0;
                else if(table[i][j] == 0) table[i][j] = INF;
            }
        }

        return table;
    }

}