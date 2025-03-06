package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2533 {
    static int n;
    static boolean[] isVisited;
    static List<Integer>[] tree;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2];  // 0: 얼리어답터 X, 1: 얼리어답터 O
        isVisited = new boolean[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int now) {
        isVisited[now] = true;
        dp[now][0] = 0;
        dp[now][1] = 1;

        for (int next : tree[now]) {
            if (!isVisited[next]) {
                dfs(next);
                dp[now][0] += dp[next][1];
                dp[now][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}
