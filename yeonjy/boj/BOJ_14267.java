package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14267 {
    public static int n, m;
    public static List<List<Integer>> parents;
    public static int[] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new ArrayList<>();
        parents.add(new ArrayList<>());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            parents.add(new ArrayList<>());
            int employee = Integer.parseInt(st.nextToken());

            if (employee != -1) {
                parents.get(employee).add(i);
            }
        }

        dp = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int employee = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            dp[employee] += value;
        }

        dfs(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dp[i] + " ");
        }
        System.out.println(sb);
    }

    private static void dfs(int idx) {
        for (int next : parents.get(idx)) {
            dp[next] += dp[idx];
            dfs(next);
        }
    }
}
