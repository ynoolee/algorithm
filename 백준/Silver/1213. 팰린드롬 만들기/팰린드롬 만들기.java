import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    /*
    * Palindrome -> '앞으로 읽으나', '뒤로 읽으나' 같은! 문자열
    *
    * 이 문제는 어떤 문자열이 주어졌을 때, 이를 사용해 팰린드롬을 만들라는 문제다
    * 지금 생각은, 그냥 '문자' 별 개수를 세어두고
    * 가장 앞, 가장 뒤에서부터 채워가야 하지 않을까 생각한다.
    * 그리고 각 문자별 개수를 세는데,
    * 홀수 개수인 문자가 2개 이상 존재하면 팰린드롬을 만들 수 없다 (0개 또는 1개 여야 한다 )
    * ACA(1) 이거나 ABBA(0) 여야 한다. 2개이면 ACDA 이런식이라..
    * */
    private void run() throws Exception {
        String name = br.readLine();
        Map<Character, Integer> map = new HashMap<>();

        // 각 글자별 개수를 카운트 한다
        for(int idx = 0; idx < name.length(); idx++) {
            int cnt = map.getOrDefault(name.charAt(idx), 0) + 1;
            map.put(name.charAt(idx), cnt);
        }

        if(isPossible(map)) {
            String palindrome = palindrome(map, name.length());
            System.out.println(palindrome);
        } else {
            System.out.println("I'm Sorry Hansoo");
        }
    }

    private boolean isPossible(Map<Character,Integer> map) {
        long oddNumber = map.keySet().stream()
            .filter(key -> map.get(key) % 2 != 0)
            .count();

        if(oddNumber > 1) return false;
        else return true;
    }

    // map, 문자열 길이 을 전달받아 palindrome 을 생성한다
    private String palindrome(Map<Character,Integer> map, int len) {
        char[] pal = new char[len];
        int pair = 0;
        int mid = len/2; // 길이가 3 이라면 idx 1 이 중간지점임


        // 각 key 에 대해 2개를 -> pal 에 세팅하는 방식으로 소모하고, map 에서 제거하던가 해야해.
        // 내부적으로 Iterator 를 사용하면서 remove 하는 것은 불가능하니까
        for(pair = 0; pair < mid; pair++) {
            char c = map.keySet().stream().sorted().filter(key -> map.get(key) >= 2).findFirst().orElse(null);
            pal[pair] = c;
            pal[len - 1 - pair] = c;

            // 해당 char 를 사용했으니, map 에 업데이트 한다
            Integer preNumb = map.get(c); // 개수를 빼기 전의 수

            if(preNumb == 2) map.remove(c);
            else map.put(c, preNumb - 2);
        }

        if(!map.isEmpty()) {
            pal[pair] = map.keySet().stream().filter(key -> map.get(key) != 0).findFirst().orElse(null);
        }

        return new String(pal);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.run();
    }
}
