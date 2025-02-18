import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 행렬 곱셈 순서 / 골드3
https://www.acmicpc.net/problem/11049
 */
public class BOJ_11049 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //행렬의 수 1 ~ 500

        int[][] matrix = new int[N + 1][2];
        int[][] dp = new int[N + 1][N + 1];  //dp[i][j]: i번째 행렬부터 j번째 행렬까지 곱하는 최소 연산 횟수

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            matrix[i][0] = r;  //i번째 행렬의 행의 크기
            matrix[i][1] = c;  //i번째 행렬의 열의 크기
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;  //같은 행렬은 연산 횟수 0
        }

        for (int len = 1; len <= N; len++) {

            for (int i = 1; i <= N - len; i++) {  //i부터 j(i + len)번째 행렬
                int j = i + len;

                for (int k = i; k < j; k++) {  //i부터 k, k + 1부터 j까지의 연산 횟수 계산
                    //i (aXb) k (bXc)  k+1 (cXd) j (dXe)
                    //i~k의 연산 횟수와 (k+1)~j의 연산 횟수에 두 그롭을 곱하는 aXcXe를 더해줘야 한다.
                    int count = dp[i][k] + dp[k + 1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1];
                    dp[i][j] = Math.min(dp[i][j], count);
                }
            }
        }

        System.out.println(dp[1][N]);
    }
}
