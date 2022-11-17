
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    /*
    * 1. 0 이 아닌 값 -> 배열에 x 라는 값을 추가하는 연산
    * 2. 0 인 경우 -> 배열에서 "절댓값이 가장 작은 값" 을 출력 && 그 값을 제거
    * ----- 절댓값이 가장 작은 값이 여러개 인 경우, -> 실제 값이 가장 작은 수
    *
    * 모든 정수는 int 로 표현 가능하다
    *
    * 출력: "0이 주어지는 횟수만큼" 답을 출력.
    * 만약 배열이 비어 있는 경우인데 '0' 연산을 하라고 하는 경우 ==> 출력 : 0
    * */

    /**
     * - 배열의 정렬 기준 1. 절댓값(작은 게 우선) 2. 실제 값(작은 게 우선)
     * Comparator 만 잘 정의해도 될 듯
     * */


    private void run() throws Exception {
        int n = Integer.parseInt(br.readLine()); // 연산의 개수

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing((Integer o1) -> Math.abs(o1)).thenComparing(o1 -> o1));

        for (int i = 0; i < n; i++) {
            int op = Integer.parseInt(br.readLine());

            operate(op, pq);
        }
    }

    private void operate(int op, PriorityQueue<Integer> pq) {
        switch (op) {
            case 0 :
                remove(pq);
                break;
            default :
                pq.add(op);
        }
    }

    private void remove(PriorityQueue<Integer> pq) {
        if(!pq.isEmpty()) {
            System.out.println(pq.poll());
        } else {
            System.out.println(0);
        }
    }


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run();
    }
}