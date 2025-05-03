package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10422 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] tests = new int[T];
        for (int i = 0; i < T; i++) {
            tests[i] = Integer.parseInt(br.readLine());
        }

        long[] dp = new long[5001];
        dp[0] = 1L;
        dp[2] = 1L;
        for (int i = 2; i < 2501; i++) {
            for (int j = 0; j < i; j++) {
                dp[i * 2] = (dp[i * 2] + (dp[j * 2] * dp[(i - 1 - j) * 2])) % 1_000_000_007L;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            sb.append(dp[tests[i]]).append("\n");
        }
        System.out.println(sb);

    }
}
