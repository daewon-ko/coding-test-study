import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 파일 합치기 / 골드3
https://www.acmicpc.net/problem/11066
 */
public class BOJ_11066 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int K = Integer.parseInt(br.readLine());  //500
            int[] files = new int[K + 1];
            int[] sum = new int[K + 1];  //누적합
            int[][] dp = new int[K + 1][K + 1];  //dp[i][j]: i번부터 j번까지 파일을 합치는데 필요한 최소 비용

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                files[i] = Integer.parseInt(st.nextToken());  //10,000
                sum[i] = sum[i - 1] + files[i];
            }

            for (int len = 2; len <= K; len++) {  //i번부터 j번까지의 길이 2 ~ K
                for (int i = 1; i <= K - len + 1; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;

                    for (int k = i; k < j; k++) {  //i ~ k, (k + 1) ~ j의 비용 계산
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1]);
                    }
                }
            }

            sb.append(dp[1][K]).append("\n");
        }

        System.out.println(sb);
    }
}
