import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 노드사이의 거리 / 골드5
https://www.acmicpc.net/problem/1240
 */
public class BOJ_1240 {

    private static List<List<Edge>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList.get(a).add(new Edge(b, c));
            adjList.get(b).add(new Edge(a, c));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(bfs(a, b)).append("\n");
        }

        System.out.println(sb);
    }

    private static int bfs(int from, int to) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[adjList.size()];
        q.add(new int[]{from, 0});
        visited[from] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curFrom = cur[0];
            int curWeight = cur[1];

            if (curFrom == to) {
                return curWeight;
            }

            for (Edge next : adjList.get(curFrom)) {
                if (!visited[next.to]) {
                    visited[next.to] = true;
                    q.add(new int[]{next.to, next.weight + curWeight});
                }
            }
        }

        return -1;
    }

    private static class Edge {

        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
