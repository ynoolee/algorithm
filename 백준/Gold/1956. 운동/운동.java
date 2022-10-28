import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 다익스트라 O(n^2LogN) 과 플로이드 워셜 O(N^3)
    // 다익스트라는 두 노드 사이의 최단 거리
    // 플로이드 워셜은 모든 노드 사이의 최단 거리

    /*
    * 다시 시작점으로 돌아오고 싶다 -> 사이클을 찾기를 원한다
    * 사이클을 이루는 도로의 길이 합이 최소가 되도록 찾을 것이다.
    * 모든 간선은 "단방향" 이다.
    * 돌아오는 길은, 왔던 길과 다를 수 있겠다.
    *
    *
    * 사이클 -->  a -> b 의 최단 거리 +  b-> a 의 최단 거리
    * d[a][b] + d[b][a] 가 최소가 되는 a-b 를 찾는 것
    *
    * 운동 경로를 찾는 것이 불가능 한 경우 ? a->b 와 b->a 모두 존재하는 (a,b)쌍이 존재하지 않는 경우
    * */

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;
    private void run() throws Exception {
        st = new StringTokenizer(br.readLine());

        int ans = Integer.MAX_VALUE;
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        // 최대 거리 -> V 는 400이고 E 는 V^2 이하인 상황 -> E 는 최대 16만개일 수 있음.최대 합 160000,0000(16억)
        int[][] d = new int[v][v]; // 거리 테이블


        // 초기화
        // 플로이드 워셜 문제에 대해 아무생각 없이 "무한" 값으로 초기화 해 놓았음
        // 그런데 다시 생각 해 보니, 굳이 MAX 값으로 초기화 할 필요가 없었던 것 같다.
        // 주어진 모든 간선은 0 보다 큰 값을 갖기 때문이다. -> 따라서 여전히 0 을 갖는 d[a][b] 는 a->b 로의 경로가 없는 것이다
        // 기존에 MAX 인지 확인하던 검증은 , 0 인지 검증하는 것으로 바꾸면 된다

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            d[a][b] = c;
        }

        floyd(d, v);

        // 모든 (a,b) 쌍을 순회하면서, d[a][b] 와 d[b][a] 모두 '무한이 x' 경우에 대하여 최소 값을 찾는다
        for(int a = 0; a < v; a++) {
            for(int b = 0; b < v; b++) {
                if(a == b) continue; // 자기 자신으로에 대한 사이클은 뛰어넘어야 해요
                if(d[a][b] != 0 && d[b][a] != 0) {
                    ans = Math.min(ans, d[a][b] + d[b][a]);
                }
            }
        }

        if(ans == Integer.MAX_VALUE) ans = -1;

        System.out.println(ans);

    }


    // 플로이드 워셜 알고리즘
    private void floyd(int[][] g, int v) {
        // Dab = min(Dab, Dax + Dxb) 입니다.
        for(int a = 0; a < v; a++) {
            for(int b = 0; b < v; b++) {
                if(a==b) continue;
                for(int x = 0; x < v; x++) {
                    if(x == a || x == b) continue;
                    if(g[a][x] != 0 && g[x][b] != 0 ) {
                        if(g[a][b] == 0) g[a][b] = g[a][x] + g[x][b];
                        else g[a][b] = Math.min(g[a][b], g[a][x] + g[x][b]);
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
