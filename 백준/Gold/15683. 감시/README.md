# [Gold IV] 감시 - 15683 

[문제 링크](https://www.acmicpc.net/problem/15683) 

### 성능 요약

메모리: 96108 KB, 시간: 384 ms

### 분류

브루트포스 알고리즘(bruteforcing), 구현(implementation), 시뮬레이션(simulation)

### 문제 설명

<p>스타트링크의 사무실은 1×1크기의 정사각형으로 나누어져 있는 N×M 크기의 직사각형으로 나타낼 수 있다. 사무실에는 총 K개의 CCTV가 설치되어져 있는데, CCTV는 5가지 종류가 있다. 각 CCTV가 감시할 수 있는 방법은 다음과 같다.</p>

<table class="table table table-bordered" style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 20%; text-align: center; vertical-align: middle;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15683/1.png" style="width: 113px; height: 70px;"></td>
			<td style="width: 20%; text-align: center;vertical-align: middle;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15683/2.png" style="width: 156px; height: 70px;"></td>
			<td style="width: 20%; text-align: center;vertical-align: middle;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15683/3.png" style="width: 100px; height: 100px;"></td>
			<td style="width: 20%; text-align: center;vertical-align: middle;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15683/4.png" style="width: 138px; height: 100px;"></td>
			<td style="width: 20%; text-align: center;vertical-align: middle;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15683/5.png" style="width: 149px; height: 150px;"></td>
		</tr>
		<tr>
			<td style="width: 20%; text-align: center;">1번</td>
			<td style="width: 20%; text-align: center;">2번</td>
			<td style="width: 20%; text-align: center;">3번</td>
			<td style="width: 20%; text-align: center;">4번</td>
			<td style="width: 20%; text-align: center;">5번</td>
		</tr>
	</tbody>
</table>

<p>1번 CCTV는 한 쪽 방향만 감시할 수 있다. 2번과 3번은 두 방향을 감시할 수 있는데, 2번은 감시하는 방향이 서로 반대방향이어야 하고, 3번은 직각 방향이어야 한다. 4번은 세 방향, 5번은 네 방향을 감시할 수 있다.</p>

<p>CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다. 사무실에는 벽이 있는데, CCTV는 벽을 통과할 수 없다. CCTV가 감시할 수 없는 영역은 사각지대라고 한다.</p>

<p>CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.</p>

<pre>0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0</pre>

<p>지도에서 0은 빈 칸, 6은 벽, 1~5는 CCTV의 번호이다. 위의 예시에서 1번의 방향에 따라 감시할 수 있는 영역을 '<code>#</code>'로 나타내면 아래와 같다.</p>

<table class="table table table-bordered" style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 25%; text-align: center;">
			<pre>0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 # 6 0
0 0 0 0 0 0</pre>
			</td>
			<td style="width: 25%; text-align: center;">
			<pre>0 0 0 0 0 0
0 0 0 0 0 0
# # 1 0 6 0
0 0 0 0 0 0</pre>
			</td>
			<td style="width: 25%; text-align: center;">
			<pre>0 0 # 0 0 0
0 0 # 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0</pre>
			</td>
			<td style="width: 25%; text-align: center;">
			<pre>0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 # 0 0 0</pre>
			</td>
		</tr>
		<tr>
			<td style="width: 25%; text-align: center;">→</td>
			<td style="width: 25%; text-align: center;">←</td>
			<td style="width: 25%; text-align: center;">↑</td>
			<td style="width: 25%; text-align: center;">↓</td>
		</tr>
	</tbody>
</table>

<p>CCTV는 벽을 통과할 수 없기 때문에, 1번이 → 방향을 감시하고 있을 때는 6의 오른쪽에 있는 칸을 감시할 수 없다.</p>

<pre>0 0 0 0 0 0
0 2 0 0 0 0
0 0 0 0 6 0
0 6 0 0 2 0
0 0 0 0 0 0
0 0 0 0 0 5</pre>

<p>위의 예시에서 감시할 수 있는 방향을 알아보면 아래와 같다.</p>

<table class="table table table-bordered" style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 25%; text-align: center;">
			<pre>0 0 0 0 0 #
# 2 # # # #
0 0 0 0 6 #
0 6 # # 2 #
0 0 0 0 0 #
# # # # # 5</pre>
			</td>
			<td style="width: 25%; text-align: center;">
			<pre>0 0 0 0 0 #
# 2 # # # #
0 0 0 0 6 #
0 6 0 0 2 #
0 0 0 0 # #
# # # # # 5</pre>
			</td>
			<td style="width: 25%; text-align: center;">
			<pre>0 # 0 0 0 #
0 2 0 0 0 #
0 # 0 0 6 #
0 6 # # 2 #
0 0 0 0 0 #
# # # # # 5</pre>
			</td>
			<td style="width: 25%; text-align: center;">
			<pre>0 # 0 0 0 #
0 2 0 0 0 #
0 # 0 0 6 #
0 6 0 0 2 #
0 0 0 0 # #
# # # # # 5</pre>
			</td>
		</tr>
		<tr>
			<td style="width: 25%; text-align: center;">왼쪽 상단 2: ↔, 오른쪽 하단 2: ↔</td>
			<td style="width: 25%; text-align: center;">왼쪽 상단 2: ↔, 오른쪽 하단 2: ↕</td>
			<td style="width: 25%; text-align: center;">왼쪽 상단 2: ↕, 오른쪽 하단 2: ↔</td>
			<td style="width: 25%; text-align: center;">왼쪽 상단 2: ↕, 오른쪽 하단 2: ↕</td>
		</tr>
	</tbody>
</table>

<p>CCTV는 CCTV를 통과할 수 있다. 아래 예시를 보자.</p>

<pre>0 0 2 0 3
0 6 0 0 0
0 0 6 6 0
0 0 0 0 0
</pre>

<p>위와 같은 경우에 2의 방향이 ↕ 3의 방향이 ←와 ↓인 경우 감시받는 영역은 다음과 같다.</p>

<pre># # 2 # 3
0 6 # 0 #
0 0 6 6 #
0 0 0 0 #
</pre>

<p>사무실의 크기와 상태, 그리고 CCTV의 정보가 주어졌을 때, CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 사무실의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 8)</p>

<p>둘째 줄부터 N개의 줄에는 사무실 각 칸의 정보가 주어진다. 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다. </p>

<p>CCTV의 최대 개수는 8개를 넘지 않는다.</p>

### 출력 

 <p>첫째 줄에 사각 지대의 최소 크기를 출력한다.</p>
 
### 문제풀이 ADD

```java

package learningJava.algorithm.baek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    /*
    * 이 문제는 빡구현이긴 한데, 백트랙킹을 사용해야만 한다고 생각했었다.
    * 그 이유는, 결국 각 CCTV 별로 방향을 돌릴 수 있고, 그 '조합' 에 따라 경우가 모두 달라지기 때문이다.
    * 따라서 나는, 재귀 호출 될 때 마다 사용할, 각 CCTV 종류에 따른 '방향별, 경우' 를 cctv 라는 리스트에 먼저 정의해 두었다.
    * 예를들어 1번은 4 가지 경우의 수(북,동,서,남)
    * 2번은 2가지 경우의수((북,남),(동,서)) 로 정의했다.
    *
    * */
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {0, -1}, {1, 0}}; // 북, 동, 서, 남

    private List<List<Integer>>[] cctv;

    private void initCctv() {
        cctv = new ArrayList[6]; // 번호는 1 번에서 6 번이니까

        cctv[1] = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            cctv[1].add(new ArrayList<>());
        }
        cctv[1].get(0).add(0);
        cctv[1].get(1).add(1);
        cctv[1].get(2).add(2);
        cctv[1].get(3).add(3);

        cctv[2] = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            cctv[2].add(new ArrayList<>());
        }
        cctv[2].get(0).add(0);
        cctv[2].get(0).add(3);
        cctv[2].get(1).add(1);
        cctv[2].get(1).add(2);

        cctv[3] = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            cctv[3].add(new ArrayList<>());
        }
        cctv[3].get(0).add(0);
        cctv[3].get(0).add(1);
        cctv[3].get(1).add(0);
        cctv[3].get(1).add(2);
        cctv[3].get(2).add(3);
        cctv[3].get(2).add(2);
        cctv[3].get(3).add(3);
        cctv[3].get(3).add(1);

        cctv[4] = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cctv[4].add(new ArrayList<>());
        }
        cctv[4].get(0).add(0);
        cctv[4].get(0).add(1);
        cctv[4].get(0).add(2);
        cctv[4].get(1).add(0);
        cctv[4].get(1).add(1);
        cctv[4].get(1).add(3);
        cctv[4].get(2).add(0);
        cctv[4].get(2).add(2);
        cctv[4].get(2).add(3);
        cctv[4].get(3).add(1);
        cctv[4].get(3).add(2);
        cctv[4].get(3).add(3);

        cctv[5] = new ArrayList<>();
        cctv[5].add(new ArrayList<>());
        cctv[5].get(0).add(0);
        cctv[5].get(0).add(1);
        cctv[5].get(0).add(2);
        cctv[5].get(0).add(3);
    }

    private List<int[]> all = new ArrayList<>(); // 모든 cctv 들을 여기다가 넣어

    private int min = Integer.MAX_VALUE; // max 비사각 지대 ( min 사각 지대 = n * m - max 비사각지대 )

    private void run() throws Exception {
        initCctv();

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] b = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
                if (b[i][j] > 0 && b[i][j] < 6) {
                    all.add(new int[]{i, j, b[i][j]});
                }
            }
        }

        next(b, n, m, 0);

        invisible(b, n, m);

        System.out.println(min);
    }


    // cctv 를 먼저 list 에 집어 넣고, 하나씩 빼는 방식으로 해 봐야 겠다.
    // 이전 방법은 cctv 위치를 덮어씌어 버려서(따라서 해당 cctv 의 위치 r,c 과 cctv번호 까지 다 넣어놔야함 ) , cctv 위치 부터 시작하는 뭔가를 하지 못하게 되었었다
    // list 에 넣고.. 꺼내...쓰기..
    // 백트랙킹 재귀호출 부분
    private void next(int[][] b, int n, int m, int i) {
        if (i == all.size()) {
            int invisible = invisible(b, n, m);
            min = Math.min(min, invisible);

            return;
        }

        int[] locations = all.get(i);
        int cur = locations[2]; // 현재 위치의 값 (이거 저장 안 해두면, 덮어씌워져 버린다 )
        // CCTV 인 칸
        for (List<Integer> comb : cctv[cur]) {
            // 기존의 보드를 복사해 새로운 보드 생성
            int[][] cb = copy(b, n, m);
            // 나는 cctv[cur] 을 List 의 List 로 정의해두었다
            // 예를들어 cctv[1].get(0) 은 (북,남) 을 볼 수 있는 cctv 이다.
            // 따라서 cctv[1] 의 comb 에 들어있는 모든 방향으로 fill 을 해야, 이 cctv 로 볼 수 있는 비사각 지대들을 모두 체크하는 것이 된다
            for (Integer dir : comb) {
                fill(dir, locations[0], locations[1], cb, n, m);
            }
            // cur 번 cctv 의 '이 방향' 을 선택한 경우, 나머지 조합을 위해 재귀호출을 수행한다
            next(cb, n, m, i + 1);
        }


    }

    /*
     * ?? 뭐지?? 이 문제에서는 '6' 도 '비사각 지대' 로 여기고 있다!!!!!!!!!
     * '0' 인 공간들을 '사각 지대'로 생각하는 것이다.
     * 현재 내 풀이는 '전체 - 비사각지대' 를 빼는 방법을 취하고 있다.
     * 그런데 6 은 마주칠 때만 break 하는 방식이라.. 때 때 마다 카운트 될 수도 아닐 수도 있을 듯 하다.
     * 그렇다고 next 함수에서 일일이 하자니, 더 헷갈린다.
     * 어차피 6은 비사각지대라면, 그냥 마지막에 한 번만 전체 6 의 개수를 세어주고, 이를 전체에서 빼주도록 하겠다
     * */
    private int invisible(int[][] b, int n, int m) {
        int cnt = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (b[r][c] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }


    // 비 사각 지대 -> 7 로 표시
    // 각 방향으로, 현재 위치에서부터 시작해서 채워 넣기
    // 벽(6) 을 만나면 멈춘다
    // 벌써 "비사각 지대인 칸" 은 넘긴다 ( count 에 세면 안됨)
    // cctv 인 칸도 7 로 채운다
    private void fill(int dir, int prevR, int prevC, int[][] b, int n, int m) {
        int r = prevR;
        int c = prevC;

        while (r < n && c < m && r >= 0 && c >= 0) {
            if (b[r][c] == 6) {
                break;
            } else if (b[r][c] >= 0 && b[r][c] < 6) {
                b[r][c] = 7;
            }

            r = r + dirs[dir][0];
            c = c + dirs[dir][1];
        }
    }

    private int[][] copy(int[][] b, int n, int m) {
        int[][] board = new int[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = Arrays.copyOf(b[i], m);
        }

        return board;
    }


    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
```

