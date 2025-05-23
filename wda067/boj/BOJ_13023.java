import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / ABCDE / 골드5
https://www.acmicpc.net/problem/13023
 */
public class BOJ_13023 {

    private static List<List<Integer>> adjList = new ArrayList<>();
    private static boolean[] visited;
    private static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        for (int i = 0; i < n; i++) {
            if (flag) {
                break;
            }
            visited = new boolean[n];
            visited[i] = true;
            dfs(1, i);
        }

        System.out.println(flag ? 1 : 0);
    }

    private static void dfs(int count, int node) {
        if (count == 5) {
            flag = true;
            return;
        }

        for (Integer next : adjList.get(node)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(count + 1, next);
                visited[next] = false;
            }
        }
    }
}
