import java.util.*;
import java.io.*;

public class Main {

    /**
     * 가중치 없는 방향 그래프에서, 모든 정점 i,j 에 대해 i 에서 j 로 가는 경로가 존재하는 지 구하는 프로그램을 작성하라 - 모든 i, j 에 대해 구하라는 것을
     * 보고 "플로이드 와샬" 로 풀기로 했다. 
     * 하지만 이 문제는 "그저 i - j 사이의 경로의 존재여부" 만을 알면 되기 때문에 최소 거리 테이블을 업데이트 해 나가지 않아도 된다  - INF 값을 둘 필요도 없다
     */

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private int n;

    private int[][] g; // graph

    private void run() throws Exception {
        input();

        solve();

    }


    // i -> j 로 가는 "경로가 존재" 한 다면 result[i][j] 를 1로 출력한다  -> result[i][i] 는 1으로 출력한다
    // 기존 플로이드 와샬은 최소 거리를 업데이트 해 가며 구하는 방식 이었다면
    // 이 문제는 "경로가 존재" 하는지만 확인 해 주면 된다
    // 경로가 존재하는 경우 d[i][j] 를 1로 세팅하자
    private void input() throws Exception {
        // 일단 이 문제는 입력이 친절하게 주어짐
        n = Integer.parseInt(br.readLine());
        g = new int[n][n];

        // 입력 자체를 행 별로 주어짐
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                g[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void solve() {
        // 플로이드 와샬 알고리즘 구현시 가장 주의해야 할 것 -> k 는 가장 바깥 루프에 존재해야 한다.
        // 그래야 , k 에 따라 Di-k-j 경로를 업데이트 해 가며 i-j 의 최단 경로를 구하기 때문이다.
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(g[i][j] == 1) continue;
                    if((g[i][k] == 1 && g[k][j] == 1 )) {
                        g[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(g[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}