import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / LCA / 골드3
https://www.acmicpc.net/problem/11437
 */
public class BOJ_11437 {

    private static final List<List<Integer>> adjList = new ArrayList<>();
    private static boolean[] visited;
    private static int[] depth;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        depth = new int[N + 1];
        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        //인접 리스트 초기화
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        //각 노드의 깊이와 부모 노드를 구한다.
        bfs(1);

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //a와 b의 LCA를 구한다.
            sb.append(lca(a, b)).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            //인접 노드 탐색
            for (Integer adjNode : adjList.get(cur)) {
                if (!visited[adjNode]) {
                    visited[adjNode] = true;
                    depth[adjNode] = depth[cur] + 1;
                    parent[adjNode] = cur;
                    queue.add(adjNode);
                }
            }
        }
    }

    private static int lca(int a, int b) {
        //a의 깊이가 더 깊도록 설정한다.
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        //a와 b의 깊이를 맞춘다.
        while (depth[a] != depth[b]) {
            a = parent[a];
        }

        //최소 공통 조상을 찾을 때까지 한 단계식 올라간다.
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

}
