import java.util.*;

class Solution {
    /**
     * 이 문제는 노드의 개수가 최대 17 개 밖에 되지 않기 때문에, DFS, BFS 를 사용한 전체탐색으로도 풀 수 있다.
     * ! 하지만 가장 중요한 점은 "다음에 방문 가능한 노드" 가 "자식 노드들에만 한정되지 않는다는 것" 이다.
     * ( 예시로 주어진 것 처럼 ,  0->2  은 불가능 했는데 0 -> 1(0의 좌식노드ㅎㅎ) -> 2(0의 우식노드 ㅎㅎ) 는 가능하고 이것까지 고려해야하기 때문..!!! )
     * !! 따라서, "현재 노드" 다음에 방문 가능한 노드는 : 사실상, "현재노드 ~ root " 까지의 경로에 있는 노드들의 자식 노드
     * !! 물론, 현재노드까지 오는 "현재 경로" 상에서 이미 방문한 자식 노드는 제외 해야 한다.
     * !! ( 얕은 복사, 깊은 복사를 신경 쓰며 - 이 다음 대상 노드들을 관리하는 게 필요할 듯 하다  -- 다양한 Collection 타입 인터페이스들에는 이미 addAll() 과 같은 메서드가 제공되고 있으니 잘 활용해 보자
     * !! ( 왜냐하면, 현재 노드 다음에 방문 가능한 "이 모든 노드들" 을 dfs 로 타고 내려갈 것이기 때문 이다 - 같은 list 를 공유하면 이상한 결과가 나올테니 )
     * !! ( 개인 적으로 이해에 도움받은 글 : https://g-egg.tistory.com/76 )
     *
     * 여기서 작성하는 DFS 를 구현하는 각 재귀 함수에서는 , 이를 호출한 함수에게 뭔가를 전달하거나 하지는 않고, 각 각의 재귀함수가 별도의 케이스라고 생각 해주어야 한다 ( 전체 탐색같은 )
     *
     * 이를 구현하기 위해 , 재귀함수에는 "경로 List" 를 전달하도록 하였다 -> 방문했던 각 노드에서의 아직 방문하지 않은 자식노드들을 더한 것이다 -> 그렇다면 분명 그 자식들 중 하나는 방문하고 왔을 것이다 -> 현재 case 에서는 그 방문한 노드들은 제거 해주어야 한다.
     * 현재 case 가 더 이상 진행 될 수 없는 경우 1) List 가 비어있는 경우 2) 현재 노드를 방문하며 sheep <= wolf 가 된 경우
     * 현재 case 에 오면 확인 해야 하는 것 1. 현재 노드를 방문 하며 sheep <= wolf 가 되었는지 ---> 아닌 경우에만 2. 현재까지의 sheep 이 max 인지 확인하기
     * 1. 가장 먼저 root 를 List 에 넣어서, 첫 번째 재귀 호출을 시작한다.
     *
     * 다음 노드를 방문할 때면, 현재 방문했던 노드를 리스트에서 삭제 해야 한다.
     * -> 그런면에서 List(O(n)) 보다는 Set(O(1)) 을 사용하는게 나을 것 같음 - 어차피 여기에 있는 노드들은, 어떤 순서로든 지금 당장 방문해도 괜찮은 애들이기 때문.
     *
     * */
    private List<List<Integer>> tree = new ArrayList<>();
    private int[] infos;
    private int max; // max sheep 의 수만 기록하면 되니까 뭐

    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;

        infos = info;

        int n = info.length; // the number of info
        for(int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        initTree(edges);

        Set<Integer> candidate = new HashSet<>();
        candidate.add(0);

        recur(0, candidate, 0,0);

        answer = max;
        return answer;
    }

    private void initTree(int[][] edges) {
        for(int[] edge: edges) {
            int p = edge[0];
            int c = edge[1];

            tree.get(p).add(c);
        }
    }

    private void recur(int n, Set<Integer> candidates, int sheep, int wolf) {
        int curSheep = sheep; int curWolf = wolf;

        if(infos[n] == 0 ) curSheep++;
        else curWolf++;

        if(curSheep <= curWolf) return;

        max = Math.max(max, curSheep);

        // deep copy
        Set<Integer> nextCandidates = new HashSet<>(candidates);

        // add child
        for(int c : tree.get(n)) {
            nextCandidates.add(c);
        }
        // remove cur node
        nextCandidates.remove(n);

        // traverse all cases
        for(int c : nextCandidates) {
            recur(c, nextCandidates, curSheep, curWolf);
        }
    }

}