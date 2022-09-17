
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private void run() throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(li(arr, n));
    }

    private int li(int[] arr, int n) {
        List<Integer> li = new ArrayList<>(n);

        for (int i = 0; i < arr.length; i++) {
            if (li.isEmpty() || li.get(li.size() - 1) < arr[i]) {
                li.add(arr[i]); // append
            } else if (li.get(li.size() - 1) > arr[i]) { // insert
                int idx = lowerBound(li, arr[i]);

                li.set(idx, arr[i]);
            }

        }

        return li.size();
    }

    // target 보다 같거나 큰 첫 번째 원소의 위치를 찾기
    private int lowerBound(List<Integer> arr, int target) {
        int len = arr.size();
        int left = 0;
        int right = len;
        int mid = 0;

        while (left < right) { //  lower bound 탐색 과정에서는 right = mid 로 mid 가 다시 탐색 범위에 속하기 때문에 left == right 는 조건에서 제외시켜야 한다
            mid = (left + right) / 2;
            if (arr.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        main.run();

    }

}
