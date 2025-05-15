import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
프로그래머스 / 가장 먼 노드 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/49189
 */
class PGS_49189 {

    private static final int INF = Integer.MAX_VALUE;

    List<List<Integer>> adjList = new ArrayList<>();
    int[] dist;
    boolean[] visited;

    public int solution(int n, int[][] edge) {
        dist = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            adjList.get(e[0]).add(e[1]);
            adjList.get(e[1]).add(e[0]);
        }

        bfs();

        int maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] != INF) {
                maxDist = Math.max(maxDist, dist[i]);
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == maxDist) {
                count++;
            }
        }

        return count;
    }

    void bfs() {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt((int[] i) -> i[1]));
        q.add(new int[]{1, 0});
        dist[1] = 0;
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int d = cur[1];

            for (int next : adjList.get(node)) {
                if (!visited[next]) {
                    int newD = d + 1;
                    if (dist[next] > newD) {
                        dist[next] = Math.min(dist[next], newD);
                        visited[next] = true;
                        q.add(new int[]{next, newD});
                    }
                }
            }
        }
    }
}
