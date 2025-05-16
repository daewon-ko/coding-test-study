import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 소형기관차 / 골드3
https://www.acmicpc.net/problem/2616
 */
public class BOJ_2616 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //50,000
        int[] cars = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cars[i] = Integer.parseInt(st.nextToken());
        }

        int K = Integer.parseInt(br.readLine());

        //누적합
        int[] sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + cars[i];
        }

        //dp[i][j]: i개의 기간차로 j번째 객차까지 선택했을 때 최대 손님 수
        int[][] dp = new int[4][N + 1];

        for (int i = 1; i <= 3; i++) {
            for (int j = i * K; j <= N; j++) {
                int cur = sum[j] - sum[j - K];  //j번째 객차까지 중 연속된 K개의 객차의 손님 수
                //dp[i][j - 1]: j번째 객차 선택 X
                //dp[i - 1][j - K] + cur: i번째 기관차가 (j-K+1) ~ j 구간을 선택
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - K] + cur);
            }
        }

        System.out.println(dp[3][N]);
    }
}