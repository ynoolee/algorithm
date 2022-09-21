import java.io.*;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static StringTokenizer st;


    public void run() throws IOException {
        String s = br.readLine();
        String t = br.readLine();

        StringBuilder sb1 = new StringBuilder(s); // s
        StringBuilder sb2 = new StringBuilder(t); // t


        while(sb2.length() != sb1.length()) {
            if(sb2.charAt(sb2.length() -1 ) == 'A') {
                sb2.delete(sb2.length() - 1, sb2.length()); // 마지막 글자를 제거한다
            } else {
                sb2.delete(sb2.length() - 1, sb2.length()); // 마지막 글자를 제거한다
                sb2 = sb2.reverse(); // 뒤집는다
            }
        }

        if (sb2.toString().equals(sb1.toString())) {
            System.out.println("1");
        } else System.out.println("0");
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }
}
