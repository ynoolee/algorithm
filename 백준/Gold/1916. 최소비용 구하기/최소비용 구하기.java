
import java.util.*;
import java.io.*;

public class Main {


    private static final long INF = 100_000 * 100_000 + 1;

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private int n; // 노드의 개수
    private int m; // 간선의 개수
    private int s; private int e;

    // g.get(idx) -> List<int[]> adjs -> adj.get(0) -> res : int[2] -> res[0] 인접 노드의 도시 번호
    private List<List<long[]>> g = new ArrayList<>(1000);

    // min distance table
    private long[] d;

    private void run() throws Exception {
        input();

        long res = dikstra(s,e);

        System.out.println(res);

    }


    private void input() throws Exception {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        d = new long[n];

        for(int i = 0; i < n; i++) {
            d[i] = INF;
            g.add(new ArrayList<>()); // create adj list of each node
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            g.get(n1).add(new long[]{n2, c});
        }

        st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken()) - 1;
        e = Integer.parseInt(st.nextToken()) - 1;
    }

    private long dikstra(int s, int e) {
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong((long[] a) -> a[1]));
        pq.add(new long[]{s,0});
        d[s] = 0;

        while(!pq.isEmpty()) {
            long[] cur = pq.poll();
            
            if(cur[0] == e) {
                break;
            }
            // 현재 노드를 거쳐 인접 노드로 가는 거리들을 구한다 -> 그게 인접노드에 대한 최소 거리인 경우, pq 에 넣는다
            for(long[] adj : g.get((int)cur[0])) {
                long c = cur[1] + adj[1];
                if(c < d[(int)adj[0]]) {
                    d[(int)adj[0]] = c;
                    pq.add(new long[]{adj[0], c});
                }
            }
        }

        return d[e];
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}