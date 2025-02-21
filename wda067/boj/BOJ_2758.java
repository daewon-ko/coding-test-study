import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 로또 / 골드4
https://www.acmicpc.net/problem/2758
 */
public class BOJ_2758 {

    private static final int MAX_N = 11;
    private static final int MAX_M = 2_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[][] dp = new long[MAX_N][MAX_M];  //dp[i][j]: 1부터 j까지의 숫자 중에 i개의 숫자를 선택하는 방법의 수

        for (int i = 1; i < MAX_M; i++) {
            dp[1][i] = 1; // 1개를 선택하는 방법은 1
        }

        for (int i = 2; i < MAX_N; i++) {
            for (int j = 1; j < MAX_M; j++) {
                for (int k = j / 2; k > 0; k--) {  //이전의 고른 수는 2배보다 작아야 한다.
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            //1 ~ m의 숫자 중 n개의 수 선택
            int n = Integer.parseInt(st.nextToken());  //1 ~ 10
            int m = Integer.parseInt(st.nextToken());  //1 ~ 2,000

            long result = 0;
            for (int i = 1; i <= m; i++) {
                result += dp[n][i];
            }
            sb.append(result).append('\n');
        }

        System.out.println(sb);
    }
}
