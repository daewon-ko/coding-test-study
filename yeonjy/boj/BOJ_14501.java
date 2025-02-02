package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        StringTokenizer st;
        int[][] counsels = new int[N + 1][2];  // [소요 기간][금액]
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            counsels[i][0] = Integer.parseInt(st.nextToken());
            counsels[i][1] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            int date = i + counsels[i][0] - 1;
            if (date <= N) {
                dp[date] = Math.max(dp[date], max + counsels[i][1]);
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
