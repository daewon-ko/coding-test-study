package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long r = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());
        int[][] map = new int[2][2];
        long[] dp = new long[N + 1];
        dp[1] = 4;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] * 4;
        }

        long result = 0;
        for (int i = N - 1; i >= 1; i--) {
            long nr = (long) (r / Math.pow(2, i));
            long nc = (long) (c / Math.pow(2, i));
            if (nr > 0) {
                result += dp[i] * 2;
            }
            result += dp[i] * nc;

            if (nr > 0) {
                r = (long) (r - Math.pow(2, i));
            }
            if (nc > 0) {
                c = (long) (c - Math.pow(2, i));
            }
        }
        result += r * 2 + c;
        System.out.println(result);
    }
}

