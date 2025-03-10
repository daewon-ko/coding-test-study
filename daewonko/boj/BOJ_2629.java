package daewonko.boj;

import java.util.Scanner;

// 백준 양팔저울
public class BOJ_2629 {


// Baekjoon 2629 - 양팔저울

    static boolean[][] dp;
    static int[] weights;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        weights = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        dp = new boolean[n + 1][40001];
        solve(0, 0);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int query = sc.nextInt();
            sb.append(dp[n][query] ? "Y " : "N ");
        }
        System.out.println(sb.toString().trim());
    }

    static void solve(int idx, int weight) {
        if (idx > n || dp[idx][weight]) return;
        dp[idx][weight] = true;
        solve(idx + 1, weight);
        solve(idx + 1, weight + weights[idx]);
        solve(idx + 1, Math.abs(weight - weights[idx]));
    }

}

