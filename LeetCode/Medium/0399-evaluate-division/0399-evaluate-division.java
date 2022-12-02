import java.util.*;

class Solution {
    /* *
    Union find 카테고리에서 본 문제라, union find 를 통한 문제 풀이가 떠올랐다 그래서 넘나 신기했음; 변수의 나눗셈 문제를 이렇게 풀다니 ㅋㅋ

    뭐냐면, 이 문제에서는 "나눗셈" 을 해야하고, 각 변수간의 관계가 주어진다.
    따라서 a/b = 2.0 , b/c = 3.0 이 주어지면 a 를 b 에 대해서 나타내고 b 는 c 에 대해 나타낼 수가 있어서, 최종적으로 a 는 c 로 나타낼 수가 있다.
    따라서 a/c 도 구할 수가 있는 것이다.
    즉, query 에 ["a","c"] 가 주어질 때, 이  a-c 가 동일한 서브그래프에 속해 있다면 , 해당 서브그래프의 최상위 루트로 표현할 수 있고 -> 1.5b / 2.0b 이런식이 되어 값을 구할 수가 있는 것이다.
    Union find 에서 find 를 약간이라도 효율적으로 만들기 위해 처리해주던 path compression 같은 개념이당.

    - 중요 : 먼저, child-parent 관계를 어떻게 정의할 것인가? -> a/b = 1.5 가 주어지면 b 는 a 의 parent, a 의 factor = 1.5 , b 는 1.0 으로 세팅한다.

    하지만 여기서 문제? 어렵다고 느낀 부분? 은 "노드 사이의 관계..weight? factor 라고해야 할까, 그 관계 사이의 값" 이었다. factor 라고 지칭하겠다.
    아무래도 구현..에 좀 약하다 보니 , 여기서도 많이 막히는 듯 하다. 어렵게 느껴지는 부분은, find 를 함에 따라, 특정 노드  a 는 점차 더 깊은 depth 의 parent 로 표현(factor)을 업데이트 해 가야 할 것 같은데, 이를 어떻게 해야할지 감이 안 잡혔다.
    그니까, 이 노드를 "어떤 부모노드에 대해 나타내는가" 에 따라서 factor 값도 달라지는데, 이를 어떻게 풀어나갈지 감이 잡히지 않았다.


    이런 경우는 없는건가?
    a/e 도 주어지고 a/d 도 주어지는 경우.. 그러면  a 의 최상위 노드는 모라고하지 ?
    c/e 도 주어지는 경우, c/d 를 구하라고 하면.. a 의 부모노드를 무엇으로 찾을 수 있는거지? 상위노드가 두 개 이상 존재하면 안 될 것 같다.
    그런 경우는 invalid 하니 주어지지 않는 것 같다..
    그러니까 만약
    1. a/e 도 주어지고 2. a/c 도 주어진다면
    이는 결국, e-c 사이 의 관계 도 주어지는 것이 된다 ==> 따라서, 이 둘 사이의 관계를 업데이트 하는 것이 된다

    * */

    /*
    * 갑자기 헷갈리고 어려웠던 부분이 있었는데, Union Find 의 경우 "새로운 관계" 가 주어졌을 때, 그니까 예를들어 1-2 사이의 edge 가 존재한다는 관계가 주어지면
    * - 현재까지 1 이 속한 서브 그래프 상의 최상위노드(최상위 부모 A)
    * - 현재까지 2 가 속한 서브 그래프 상의 최상위노드(최상위 부모 B)
    * 를 찾아서, 이 최상위 부모 간의 관계만 설정해 주어도 됐었다.
    *
    * 이렇게 하더라도, 나중에 2 - 3 이라는 또 다른 관계가 주어지면 2 에 대한 최상위 노드를 찾는 과정에서 , 이제는 2에 대한 최상위 부모로의 탐색을 1까지 하게 되기 때문이다.
    * 그런데, 이 문제의 경우, "관계를 설정" 할 때면 factor 도 업데이트를 해 줘야 한다.
    * B 의 parent 로 A 를 설정하게 되었을 때, factor 는 어떻게 설정할 것인가?
    *
    * a/b, c/b 와 f/e 가 주어졌을 때, c/f 를 구하라고 한다면?
    * 원래 유니온 파인드에서는 각 노드의 최상위 부모노드 간에 관계를 설정했는데... 이 문제는
    * - "c" 를 "f 의 최상위 부모노드로" 나타내야 하는 것인지
    * - "c 의 최상위 부모 노드" 를 "f 의 최상위 부모 노드로" 나타내야 하는 것인지
    * 잘 모르겠었다.
    *
    * 원래 유니온 파인드와 같이 풀려면
     * - "c 의 최상위 부모 노드" 를 "f 의 최상위 부모 노드로" 나타내야 하는 것이 맞는 것 같은데.... 이를 나타내 보자
     *
     * 예를들어 이제까지 주어진 관계상(a/b, c/b 와 f/e 가 주어졌을 때) 아래와 같은 서브 그래프가 존재하니까
     * --b     그리고  -e
     * -a-c          -f
     * c = 1.5b
     * f = 0.3e 라고 해 보자, 그리고 지금 주어진 것은 c/f = 0.5
     * 1.5b/0.3e = 0.5 를 통해, 부모간의 관계를 업데이트 해야 한다. 즉, 여기서는 b = 0.5 x 0.3 / 1.5 인 factor 를 가지면서 e 를 parent 로 갖게 되는 것이다.
     * - b 의 부모로서 e 를 세팅하고
     * - b 의 factor 는 (c/f 의 factor) * (f 가 갖고 있던 factor) / ( c 가 갖고 있던 factor)
     *
     * */

    /*
    Discussion 을 봤다.. 되게 잘 정리해 놓은 글이 있었당.  https://leetcode.com/problems/evaluate-division/discuss/147281/Java-Union-Find-solution-faster-than-99
     */

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] ans = new double[queries.size()];

        // 아직 몇 개의 노드가 존재하는지는 모르고, a 에 대한 parent 를 바로 찾으려면 Map 을 사용하는게 좋을 것 같다. factor 역시 ㅇㅇ
        Map<String, String> p = new HashMap<>();
        Map<String, Double> factor = new HashMap<>();
        int idx = 0;

        for(List<String> eq : equations) {
            // eq.get(0) 이 "a", eq.get(1) 이 "b"
            String n1 = eq.get(0);
            String n2 = eq.get(1);

            union(n1, n2, p, factor, values[idx++]);
        }

        idx = 0;
        // queries 를 구해야 한다
        for(List<String> q : queries) {
            String n1 = q.get(0);
            String n2 = q.get(1);

            //equations 에 주어지지 않았던 원소라면 -1 을 출력해야 한다
            if(!p.containsKey(n1) || !p.containsKey(n2)) {
                ans[idx++] = -1.0;
                continue;
            }

            // 공동의 노드로 나타낼 수 있는가?
            String p1 = find(n1, p, factor);
            String p2 = find(n2, p, factor);

            if(!p1.equals(p2)) {
                // 나타낼 수 없는 경우는 정답을 구할 수 없기 때문에 -1 을 출력한다
                ans[idx] = -1.0;
            } else {
                // 공동의 노드에 대한 값으로 나타내야 한다
                ans[idx] = factor.get(n1) / factor.get(n2);
            }

            idx++;
        }

        return ans;
    }

    // 두 노드의 관계를 받아, 이들을 하나의 그래프로 속하게 만드는 연산을 수행한다
    // parent 는 n2 라고 하자
    private void union(String n1, String n2, Map<String, String> p, Map<String, Double> f, double val) {
        String p1 = find(n1, p, f);
        String p2 = find(n2, p, f);

        double f1 = f.get(n1);
        double f2 = f.get(n2);

        //Update
        p.put(p1,p2);
        f.put(p1, val * f2 / f1);
    }

    // 기존의 유니온 파인들와 달리 find 하는 과정에서, 업데이트 시켜줘야하는 것에 factor 라는 것이 추가적으로 더 존재 한다
    private String find(String n1, Map<String, String> p, Map<String, Double> f) {
        if(!f.containsKey(n1)) {
            // 초기화
            p.put(n1,n1);
            f.put(n1, 1.0);

            return n1;
        }
        String originParent = p.get(n1);

        // 자기자신이 부모인 경우
        if(originParent.equals(n1)) {
            return originParent;
        }

        // 부모를 타고 타고 올라가야 한다. 재귀적으로 호출한 과정에서 부모 쪽은 이미 (현재까지의 )최상위 노드 값에 대한 factor 로 업데이트 된다
        String topParent = find(originParent, p, f);

        // Update
        p.put(n1, topParent);
        f.put(n1, f.get(originParent) * f.get(n1));

        return topParent;
    }
}