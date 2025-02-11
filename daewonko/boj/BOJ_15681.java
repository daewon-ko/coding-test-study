package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15681 {
    static int n,r, q;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean [] visited;
    static int [] subtreeNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        subtreeNode = new int[n + 1];

        for (int i = 0; i <= n + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
            list.get(v).add(u);
        }

        dfs(r);

        for (int i = 0; i < q; i++) {
            int queryNode = Integer.parseInt(br.readLine());
            System.out.println(subtreeNode[queryNode]);

        }
    }

    public static int dfs(int start) {
        visited[start] = true;
        subtreeNode[start] = 1;

        List<Integer> linkedNode = list.get(start);
        for (int neighbor : linkedNode) {
            if (!visited[neighbor]) {
                subtreeNode[start] += dfs(neighbor);
            }
        }

        return subtreeNode[start];
    }
}
