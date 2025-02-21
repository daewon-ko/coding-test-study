package daewonko.boj;
import java.util.*;
public class BOJ_2758 {


        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int T = sc.nextInt();

            while (T-- > 0) {
                int N = sc.nextInt();
                int M = sc.nextInt();

                long[][] dp = new long[N + 1][M + 1];

                for (int i = 1; i <= M; i++) {
                    dp[1][i] = 1;
                }

                for (int i = 2; i <= N; i++) {
                    for (int j = 1; j <= M; j++) {
                        dp[i][j] = dp[i][j - 1];
                        if (j / 2 > 0) {
                            dp[i][j] += dp[i - 1][j / 2];
                        }
                    }
                }

                long result = 0;
                for (int i = 1; i <= M; i++) {
                    result += dp[N][i];
                }
                System.out.println(result);
            }
            sc.close();
        }
    }

