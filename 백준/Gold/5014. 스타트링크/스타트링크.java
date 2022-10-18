
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

// 경주 조타
public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private StringTokenizer st;

    private void run() throws Exception {
        /**
         * F 층으로 이루어진 건물의 G 층에 위치한 목적지
         * 강호는 현재 S 층
         * 엘리베이터의 버튼은 U, D 밖에 없음 -> U 버튼은 "위로 U 층만큼을 이동", D 버튼은 "아래로 D 만큼을 이동"
         * - 위에 해당하는 층이 없으면 U 를 눌러도 움직이지 않는다
         * - 아래에 해당하는 층이 없으면 D 를 눌러도 움직이지 않는다
         *
         * G 층에 도착하려면, 버튼을 "적어도 몇 번 눌러야 하는지" 구하라.
         * 구할 수 없는 경우 "use the stairs" 를 출력하라
         * */

        /*
        * 생각
        * - 결국, 위, 아래로만 이동이 가능하다.
        * - 어떤 층으로부터 이동 가능한 경우의 수 ==> 2가지
        * - 처음에는 dp 인 줄 알았다. 이미 방문한 곳은 방문하지 않아야 하니까..
        * - 근데 생각해보니 이미 해당 층을 지나갔었다면 , 현재의 경우가 최소의 경우는 아닐 것이었다 -> 재방문을 못하게 visit 체크만 해 줘도 되는 것
        * --- 따라서 bfs, dfs 를 해 보 기록 하였다.
        * */

        /**
         * dp 에 대한 초기값 설정이 어렵게 느껴졌다
         * */
        int f = 0; int s = 0; int g = 0; int u = 0; int d = 0;
        boolean[] visit;

        st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken()); // 건물의 총 층 수 -> 가장 높은 층
        s = Integer.parseInt(st.nextToken()); // 강호의 현재 층 수
        g = Integer.parseInt(st.nextToken()); // 목적지인 층
        u = Integer.parseInt(st.nextToken()); // 위로 이동하는 층의 개수
        d = Integer.parseInt(st.nextToken()); // 아래로 이동하는 층의 개수
        visit = new boolean[f + 1];
        visit[s] = true; // 현재 층은 이미 방문했다

        int res = bfs(visit, f, s, g, u, d);

        if(res == -1) {
            bw.write("use the stairs");
        } else {
            bw.write(Integer.toString(res));
        }

        bw.flush();
        bw.close();
    }

    private int bfs(boolean[] visit, int top, int cur, int target, int u, int d) {
        Deque<int[]> q = new ArrayDeque<int[]>();
        q.add(new int[]{0, cur}); // depth, 현재 노드
        visit[cur] = true;

        while(!q.isEmpty()) {
            int[] got = q.poll();
            if(got[1] == target) return got[0];

            // got[1] 층에서 위로 u 만큼 위로 올라갈 수 있는 경우
            if(got[1] + u <= top && !visit[got[1] + u]) {
                visit[got[1] + u] = true;
                q.add(new int[]{got[0] + 1, got[1] + u});
            }
            // got[1] 층에서 아래로 d  만큼 내려갈 수 있는 경우
            if(got[1] - d >= 1 && !visit[got[1] - d]) {
                visit[got[1] - d] = true;
                q.add(new int[]{got[0] + 1, got[1] - d});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
