import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private StringTokenizer st;

    private int water = 0;

    private int width = 0;

    private List<Place> locations = new ArrayList<>();

    private class Place {

        int s;

        int e;

        Place(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    private void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        water = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        for (int i = 0; i < water; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()); // s
            int e = Integer.parseInt(st.nextToken()); // e

            locations.add(new Place(s, e));
        }

        locations.sort(Comparator.comparing(place -> place.s));
    }

    private void run() throws Exception {

        input();

        int last = -1; // 널빤지가 덮는 마지막 위치
        int sum = 0; // 널빤지의 개수

        for (Place l : locations) {
            // 널빤지가 형재 웅덩이의 e 까지 덮을 수 없는 경우 - 공간을 생각
            if (last < l.e ) {
                // 처음(s) 부터 시작해서 다 덮어야 하는 경우
                if (last <= l.s) {
                    int cnt = (int) Math.ceil((double) (l.e - l.s) / width);
                    sum += cnt;
                    last = l.s + cnt * width;
                } else {
                    // 현재 웅덩이의 일부는 , 이제까지의 널빤지가 어느정도 덮어 줄 수 있는 경우
                    int cnt = (int) Math.ceil((double) (l.e - last) / width);
                    sum += cnt;
                    last += cnt * width;
                }
            }
        }

        System.out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}