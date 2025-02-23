package pkl0912.pgs;

import java.util.*;

class PGS_214289 {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int temp = temperature > t2 ? t1 - (temperature - t2) : temperature;
        int INF = 987654321;
        t1 -= temp;
        t2 -= temp;
        temp = 0;

        int[][] dp = new int[onboard.length][t2 + 2];
        for (int i = 0; i < onboard.length; i++)
            Arrays.fill(dp[i], INF);
        dp[0][0] = 0;
        for (int i = 1; i < onboard.length; i++) {

            for (int j = 0; j <= t2 + 1; j++) {
                if (onboard[i] == 1 && (j < t1 || j > t2)) continue;
                int min = INF;
                if (j == 0) {
                    min = Math.min(min, dp[i-1][j]);
                    if (j + 1 <= t2 + 1) min = Math.min(min, dp[i-1][j+1]);
                }
                else {
                    if (j - 1 >= 0) min = Math.min(min, dp[i-1][j-1] + a);
                    min = Math.min(min, dp[i-1][j] + b);
                    if (j + 1 <= t2 + 1) min = Math.min(min, dp[i-1][j+1]);
                }
                dp[i][j] = min;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int j = 0; j <= t2 + 1; j++)
            result = Math.min(result, dp[onboard.length-1][j]);
        return result;
    }
}
