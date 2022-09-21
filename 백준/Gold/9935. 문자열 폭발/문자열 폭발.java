import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder("");
    public static String src,target;
    public static Deque<Character> stack = new LinkedList<>();
    public static void setting() throws IOException {
        src = br.readLine();
        target = br.readLine();
    }
    public static void solve() throws IOException {
        for(int j=0;j<src.length();j++) {
            if(src.charAt(j)==target.charAt(target.length()-1)){
                // iterate해 나간다. target.charAt(target.length()-1)) 에 있는 character와 비교하여, 같은 순간에는, stack에서 순차적으로 n-1개를 꺼낸다
                // 꺼내는 도중에, target(i)와 같지 않다면 -> 다시 stack에 넣어줘야 한다.
                for (int i = target.length() - 2; i >= 0; i--) {
                    if(stack.isEmpty()){
                        for (int re = i + 1; re < target.length(); re++) stack.add(target.charAt(re));
                        break;
                    }
                    if (stack.getLast() != target.charAt(i)) {
                        // repush
                        for (int re = i + 1; re < target.length(); re++) stack.add(target.charAt(re));
                        // fail
                        break;
                    } else {
                        // pop
                        stack.pollLast();
                    }
                }
            }else{
                stack.add(src.charAt(j));
            }
        }
        while(stack.isEmpty()==false){
            sb.append(stack.pollFirst());
        }
        if(sb.length()==0)sb.append("FRULA");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static void main(String[] args)throws IOException {
        setting();
        solve();

    }
}