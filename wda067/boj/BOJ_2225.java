import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 합분해 / 골드5
https://www.acmicpc.net/problem/2225
 */
public class BOJ_2225 {

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K + 1][N + 1];  //dp[k][n]: k개의 수로 n을 만드는 방법 수

        for (int i = 0; i <= K; i++) {
            dp[i][0] = 1;  //0을 만드는 방법은 '모두 0을 고르는' 방법 1가지
        }

        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                dp[k][n] = (dp[k][n - 1] + dp[k - 1][n]) % MOD;
            }
        }

        System.out.println(dp[K][N]);
    }
}
