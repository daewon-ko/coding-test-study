package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N - 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int answer = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N + 1][21];
        dp[1][arr[1]] = 1;

        for (int i = 1; i <= N - 2; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i][j] != 0) {
                    int plus = j + arr[i + 1];
                    int minus = j - arr[i + 1];
                    if (plus >= 0 && plus <= 20) {
                        dp[i + 1][plus] += dp[i][j];
                    }
                    if (minus >= 0 && minus <= 20) {
                        dp[i + 1][minus] += dp[i][j];
                    }
                }
            }
        }
        System.out.println(dp[N - 1][answer]);
    }
}
