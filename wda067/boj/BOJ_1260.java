import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / DFS와 BFS / 실버2
https://www.acmicpc.net/problem/1260
 */
public class BOJ_1260 {

    private static List<List<Integer>> adjList = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  //정점의 수
        int M = Integer.parseInt(st.nextToken());  //간선의 수
        int V = Integer.parseInt(st.nextToken());  //시작 정점

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        //인접 리스트 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        for (List<Integer> list : adjList) {
            Collections.sort(list);
        }

        visited = new boolean[N + 1];
        dfs(V);
        System.out.println();
        visited = new boolean[N + 1];
        bfs(V);
    }

    private static void dfs(int node) {
        System.out.print(node + " ");

        visited[node] = true;
        for (Integer adjNode : adjList.get(node)) {
            if (!visited[adjNode]) {
                dfs(adjNode);
            }
        }
    }

    private static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            System.out.print(cur + " ");

            for (Integer adjNode : adjList.get(cur)) {
                if (!visited[adjNode]) {
                    queue.add(adjNode);
                    visited[adjNode] = true;
                }
            }
        }
    }

}