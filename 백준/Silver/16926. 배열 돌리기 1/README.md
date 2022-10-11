# [Silver I] 배열 돌리기 1 - 16926 

[문제 링크](https://www.acmicpc.net/problem/16926) 

### 성능 요약

메모리: 300176 KB, 시간: 1808 ms

### 분류

구현(implementation)

### 문제 설명

<p>크기가 N×M인 배열이 있을 때, 배열을 돌려보려고 한다. 배열은 다음과 같이 반시계 방향으로 돌려야 한다.</p>

<pre>A[1][1] ← A[1][2] ← A[1][3] ← A[1][4] ← A[1][5]
   ↓                                       ↑
A[2][1]   A[2][2] ← A[2][3] ← A[2][4]   A[2][5]
   ↓         ↓                   ↑         ↑
A[3][1]   A[3][2] → A[3][3] → A[3][4]   A[3][5]
   ↓                                       ↑
A[4][1] → A[4][2] → A[4][3] → A[4][4] → A[4][5]</pre>

<p>예를 들어, 아래와 같은 배열을 2번 회전시키면 다음과 같이 변하게 된다.</p>

<pre>1 2 3 4       2 3 4 8       3 4 8 6
5 6 7 8       1 7 7 6       2 7 8 2
9 8 7 6   →   5 6 8 2   →   1 7 6 3
5 4 3 2       9 5 4 3       5 9 5 4
 <시작>         <회전1>        <회전2></pre>

<p>배열과 정수 R이 주어졌을 때, 배열을 R번 회전시킨 결과를 구해보자.</p>

### 입력 

 <p>첫째 줄에 배열의 크기 N, M과 수행해야 하는 회전의 수 R이 주어진다.</p>

<p>둘째 줄부터 N개의 줄에 배열 A의 원소 A<sub>ij</sub>가 주어진다.</p>

### 출력 

 <p>입력으로 주어진 배열을 R번 회전시킨 결과를 출력한다.</p>

# 풀이
## 문제 잘못 이해함 ㅎ;; ( 그래서 요건 잘못된 풀이 )

문제를 잘 읽자.. 심지어 이거 하나 구현하는데 45 분 걸림 ㅎㅎ

그냥 배열 전체를 시계 반대 방향으로 회전하는 문제인 줄 알았음 ㅎ;; 

```java
public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private StringTokenizer st;

    private void run() throws Exception {
        int n = 0; int m = 0; int r = 0;
        int[][] board;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for(int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < m; c++) {
                board[row][c] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i <= r ; i++) {
            board = rotate(board);
            print(board);
        }

        print(board);
    }

    private void print(int[][] board) {
        System.out.println("=================================");
        int r = board.length;
        int c = board[0].length;

        for(int[] arr: board) {
            for(int e : arr) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    // rc -> 회전하기 전의 배열의 row 수(n),col 수(m)
    private int[][] rotate(int[][] board) {
        int n = board.length; int m = board[0].length;
        int[][] next = new int[m][n]; // 회전한 결과 나오는 배열

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(c == 0) {
                    // c = 0 인 원소들 -> r = m , c = 이전 r
                    next[m - 1][r] = board[r][0];
                } else if( c == m ) {
                    // c = m 이던 원소들 -> r = 0, c = 이전 r
                    next[0][r] = board[r][m - 1];
                } else {
                    next[m - 1 - c][r] = board[r][c];
                }
            }
        }

        return next;
    }
    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
```

## 문제를 이해하자

1. 껍질의 개수 
2. 각 껍질의, 각 변에서 이동해야 하는 원소의 개수 

의 규칙만 알면 풀 수 있음 ! 

> 1 번 규칙 ~
> 
- • min(N, M) mod 2 = 0  가 주어진 이유 → 요런 경우는 없다는 고임 ㅎ(가로 or 세로가 1 이라서 rotate 가 안되는 경우 )
    
    ![image](https://user-images.githubusercontent.com/53856184/194980369-fbfd47e4-c381-4ef3-a1d4-916e4634ce2a.png)

    
    
- 껍질로 생각하면, min(n,m) / 2 만큼의 껍질을 rotate 시켜야함.
    
    아래 예시의 경우, min(n,m) /2 = 2 만큼의 껍질
    
    ![image](https://user-images.githubusercontent.com/53856184/194980385-6adc5e85-d03f-43ee-84c0-d019549fcbaa.png)
    
    아래의 경우는 min(4,3)/2 = 1 이므로 껍질 하나만 rotate 하는 거임. 
    
    ![image](https://user-images.githubusercontent.com/53856184/194980403-bbbed1e3-5aa5-4538-893e-cda5e8076d7a.png)
    

> 2번 규칙 ~
> 
> - cnt 는 지금 몇 번째 껍질인지를 나타낸다고 하자 ~
- 아래로 이동
    - 시작 위치 : (cnt, cnt)
    - 몇 개의 원소를 이동 ? → n - cnt - 1
- 오른쪽으로 이동
    - 시작위치 : (n-1-cnt, cnt)
    - 몇 개의 원소를 이동 ? → m - cnt - 1
- 위쪽으로 이동
    - 시작 위치 :  (n-1-cnt, m - 1- cnt)
    - 몇 개의 원소를 이동 ? → n - cnt - 1
- 왼쪽으로 이동
    - 시작 위치 :  (cnt, m - 1- cnt)
    - 몇 개의 원소를 이동 ? → m - cnt - 1
