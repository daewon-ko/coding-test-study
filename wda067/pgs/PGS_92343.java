import java.util.ArrayList;
import java.util.List;

/*
프로그래머스 / 양과 늑대 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/92343
 */
class PGS_92343 {

    private static List<List<Integer>> adjList = new ArrayList<>();
    private static int[] info;
    private static int maxSheep;

    public int solution(int[] info, int[][] edges) {
        this.info = info;

        for (int i = 0; i < info.length; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
        }

        List<Integer> path = new ArrayList<>();
        path.add(0);  //루트 노드 추가

        dfs(1, 0, path);  //루트 노드는 양

        return maxSheep;
    }

    private static void dfs(int sheep, int wolf, List<Integer> path) {
        if (sheep <= wolf) {  //양이 늑대보다 작거나 같으면 중지
            return;
        }

        //현재 경로로 가능한 모든 경로 탐색
        for (int i = 0; i < path.size(); i++) {
            int cur = path.get(i);

            for (int next : adjList.get(cur)) {  //자식 노드 탐색
                if (!path.contains(next)) {
                    path.add(next);

                    if (info[next] == 0) {  //0 -> 양 카운트
                        dfs(sheep + 1, wolf, path);
                    } else {  //1 -> 늑대 카운트
                        dfs(sheep, wolf + 1, path);
                    }

                    path.remove(path.size() - 1);
                }
            }
        }

        maxSheep = Math.max(maxSheep, sheep);
    }
}