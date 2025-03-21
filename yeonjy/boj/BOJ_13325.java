package yeonjy.boj;

import java.io.*;
import java.util.*;

class BOJ_13325 {
    static int k, n;
    static long[] edge, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        n = (int) (Math.pow(2, k + 1) - 2);
        edge = new long[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            edge[i] = Long.parseLong(st.nextToken());
        }
        dp = new long[n + 1];
        System.out.println(dfs(0));
    }

    static long dfs(int e) {
        if (e >= (1 << k) - 1 && e <= (1 << (k + 1)) - 2) {
            return 0;
        }
        long left = dfs(e * 2 + 1);
        long right = dfs(e * 2 + 2);

        dp[e] = Math.max(dp[e * 2 + 1] + edge[e * 2 + 1], dp[e * 2 + 2] + edge[e * 2 + 2]);
        long even = Math.abs(dp[e * 2 + 1] + edge[e * 2 + 1] - dp[e * 2 + 2] - edge[e * 2 + 2]);

        return left + right + edge[e * 2 + 1] + edge[e * 2 + 2] + even;
    }

}
