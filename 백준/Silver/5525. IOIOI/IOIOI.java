import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private StringTokenizer st;


    public void run() throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) == 'O') {
                continue;
            }
            int k = 0;

            // 현재 i 로부터 OI 를 확인해야 하니, 길이가 가능한지 확인 해 본다
            while (i < s.length() - 2 && s.charAt(i + 1) == 'O' && s.charAt(i + 2) == 'I') {
                k++;

                // OI 가 N 개 연속해 있는 경우
                if (k == n) {
                    cnt++;
                    k--;
                }
                i += 2;
            }
            // 연속해서 OI 가 N 개 나오는 것이 실패한 경우 , for 문을 통해 i 는 1 증가한다. 해당 위치부터 다시 처음부터 확인하면 된다.
        }

        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }
}