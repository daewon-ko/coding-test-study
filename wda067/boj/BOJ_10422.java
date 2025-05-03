import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
백준 / 괄호 / 골드4
https://www.acmicpc.net/problem/10422
 */
public class BOJ_10422 {

    private static final int MOD = 1_000_000_007;
    private static final int MAX = 2500;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[MAX + 1];  //dp[i]: 길이가 2 * i인 올바른 괄호 문자열의 개수
        dp[0] = 1;  //빈 문자열 1개

        for (int i = 1; i <= MAX; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = (dp[i] + dp[j] * dp[i - 1 - j]) % MOD;
            }
        }

        for (int testCase = 0; testCase < T; testCase++) {
            int L = Integer.parseInt(br.readLine());

            if (L % 2 != 0) {
                System.out.println(0);
                continue;
            }

            System.out.println(dp[L / 2]);
        }
    }
}
