import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
프로그래머스 / 전력망을 둘로 나누기 / Level 2
https://school.programmers.co.kr/learn/courses/30/lessons/86971
 */
class PGS_86971 {
    public int solution(int n, int[][] wires) {
        int minDiff = Integer.MAX_VALUE;
        List<List<Integer>> graph = new ArrayList<>();

        //그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        //그래프 구성
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        //각 간선을 하나씩 제거하며 확인
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            //간선 제거
            graph.get(a).remove(Integer.valueOf(b));
            graph.get(b).remove(Integer.valueOf(a));

            //BFS로 서브트리 크기 탐색
            int subtreeSize = bfs(n, graph, a);
            int diff = Math.abs((n - subtreeSize) - subtreeSize);
            minDiff = Math.min(minDiff, diff);

            //간선 복구
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return minDiff;
    }

    private int bfs(int n, List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++;
                }
            }
        }
        return count;
    }
}

