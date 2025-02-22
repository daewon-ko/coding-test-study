package daewonko.boj;
import java.util.*;
import java.io.*;
public class BOJ_2482 {


        static int N, K;
        static int[][] dp;
        static final int MOD = 1_000_000_003;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            K = Integer.parseInt(br.readLine());

            dp = new int[N + 1][K + 1];

            for (int i = 0; i <= N; i++) {
                dp[i][0] = 1;
                dp[i][1] = i;
            }

            for (int i = 2; i <= N; i++) {
                for (int j = 2; j <= K; j++) {
                    dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
                }
            }

            System.out.println((dp[N - 1][K] + dp[N - 3][K - 1]) % MOD);
        }
    }

