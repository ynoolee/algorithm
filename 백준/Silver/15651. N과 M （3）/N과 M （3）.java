import java.io.*;
import java.util.*;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private char[] chars;
    private int n;
    private int m;
    private Set<String> set = new HashSet<>();

    public void run() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        chars = new char[m];

        recur(0);
        bw.flush();
    }

    // cnt 번째 수를 선택해야 하는 경우
    public void recur(int cnt) throws IOException {
        if(cnt == m) {
            // 이미 존재하는 경우 패쓰
            String str = new String(chars);

            if(set.contains(str)) return;
            print(); return;
        }

        for(int i = 1; i <= n; i++) {
            chars[cnt] = (char)('0' + i);
            recur(cnt + 1);
        }
    }

    private void print() throws IOException {
        // chars 를 출력한다
        for(char c : chars) {
            bw.write(c + " ");
        }
        bw.write("\n");
    }
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }
}