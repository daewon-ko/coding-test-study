import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
백준 / 색상환 / 골드3
https://www.acmicpc.net/problem/2482
 */
public class BOJ_2482 {

    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //4 ~ 1,000
        int K = Integer.parseInt(br.readLine());  //1 ~ N

        if (K > N / 2) {
            System.out.println(0);
            return;
        }

        int[][] dp = new int[N + 1][K + 1];  //dp[i][j]: i개의 색 중에서 j개를 선택하는 경우의 수

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;  //색을 하나도 선택하지 않는 경우의 수 1
            dp[i][1] = i;  //색을 1개만 선택하는 경우의 수 i
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                //i번째 색을 선택하면 i-1번째 색은 선택할 수 없으므로 dp[i-2][j-1]
                //i번째 색을 선택하지 않으면 i-1번째 색을 선택 dp[i-1][j]
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }

        //첫 번째 색을 선택하면 두 번째 색과 N번째 색은 선택할 수 없으므로 dp[N-3][K-1]
        //첫 번째 색을 선택하지 않는 경우 dp[N-1][K]
        int result = (dp[N - 1][K] + dp[N - 3][K - 1]) % MOD;
        System.out.println(result);
    }
}
