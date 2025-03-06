package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2533 {
    static int n;   // 정점(노드) 개수
    static List<List<Integer>> list = new ArrayList<>();
    static int [][] dp; // dp[i][0] : i번째 노드가 얼리어답터가 아닐 때, dp[i][1] : i번째 노드가 얼리어답터일 때
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];


        for (int i = 1; i <=n + 1; i++) {
            list.add(new ArrayList<>());
        }

        dp = new int[n + 1][2];



        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.get(u).add(v); // 양방향 관계
            list.get(v).add(u); // 양방향 관계
        }


        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));


    }

    public static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;

        List<Integer> children = list.get(node);
        for (int child : children) {
            if (!visited[child]) {
                dfs(child);
                dp[node][0] += dp[child][1];
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            }

        }


    }
}
