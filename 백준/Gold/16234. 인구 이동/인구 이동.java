

import java.util.*;
import java.io.*;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;
    private String input;


    private int l; private int r; private int n;
    private int[][] maps;
    private boolean[][] visit;
    private final int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    private void run() throws Exception {
        input();

        int day = 0;
        while(one()){
            day++;

            // init visit
            for(int r = 0; r < n; r++) {
                Arrays.fill(visit[r], false);
            }
        }

        System.out.println(day);
    }

    private boolean one() {
        boolean isMoved = false; // 한 번이라도 true 가 되면 한 번이라도 인구 이동이 있었다는 것
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(!visit[r][c]) {
                    isMoved = bfs(r,c) == false ? isMoved : true;
                }
            }
        }

        return isMoved;
    }


    private boolean bfs(int cr, int cc) {
        boolean isMoved = false;
        int sum = 0;

        Deque<int[]> union = new LinkedList<>(); // 연합국가 정보
        Deque<int[]> q = new LinkedList<>();
        q.add(new int[]{cr,cc});

        while(!q.isEmpty()) {
            int[] c  = q.poll();

            if(visit[c[0]][c[1]]) continue;

            visit[c[0]][c[1]] = true;
            sum += maps[c[0]][c[1]];
            union.add(new int[]{c[0], c[1]});

            for(int dir = 0; dir < dirs.length; dir++) {
                int nr = c[0] + dirs[dir][0];
                int nc = c[1] + dirs[dir][1];

                if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if(visit[nr][nc]) continue;

                if(isRange(c[0], c[1], nr, nc)) {
                    isMoved = true;

                    q.add(new int[]{nr,nc});
                }
            }
        }

        updatePopulation(union, sum);
        union.clear();

        return isMoved;
    }


    private boolean isRange(int cr, int cc, int nr, int nc) {
        int diff = Math.abs(maps[cr][cc] - maps[nr][nc]) ;
        if( diff >= l && diff <= r) return true;
        return false;
    }

    private void updatePopulation(Deque<int[]> unions, int sum) {

        if(unions.size() == 0) return;
        int each = sum / unions.size();

        while(!unions.isEmpty()) {
            int[] cur = unions.poll();

            maps[cur[0]][cur[1]] = each;
        }
    }

    private void input() throws Exception {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        maps = new int[n][n];
        visit = new boolean[n][n];

        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < n; c++) {
                maps[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }



    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
 }