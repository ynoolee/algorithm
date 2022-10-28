
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 그냥 복습 겸..
    // 다익스트라 O((V+E)LogV) 과 플로이드 워셜 O(V^3)
    // 다익스트라는 두 노드 사이의 최단 거리
    // 플로이드 워셜은 모든 노드 사이의 최단 거리를 구할 수 있다

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
        for(int a = 0; a < v; a++) {
            for(int b = 0; b < v; b++) {
                d[a][b] = Integer.MAX_VALUE;
                d[b][a] = Integer.MAX_VALUE;
                if(a==b) d[a][b] = 0; // 자기자신 -> 자신 : 비용 0 이죠
            }
        }
        // 현재 직접적으로 연결되어 있지 않은 두 노드 a,b 에 대해서는 d[a][b] 는 Integer.MAX_VALUE 를 갖고 있다

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
                if(d[a][b] != Integer.MAX_VALUE && d[b][a] != Integer.MAX_VALUE) {
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
        // 오랜만에 푸니, 의문인 점이 있는데.. Integer.MAX_VALUE 로 해두면 Dax + Dxb 로 스택 오버 플로우가 일어나지 않나요?
        // 내가 만약 "무한" 값을 Integer.MAX_VALUE 로 해 두었다면, Dax or Dxb 중 하나가 "무한"인지 확인하는 과정도 필요할 것 같아요
        for(int a = 0; a < v; a++) {
            for(int b = 0; b < v; b++) {
                for(int x = 0; x < v; x++) {
                    if(x == a || x == b) continue;
                    if(g[a][x] != Integer.MAX_VALUE && g[x][b] != Integer.MAX_VALUE) {
                        g[a][b] = Math.min(g[a][b], g[a][x] + g[x][b]);
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
