import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 점수따먹기 / 골드4
https://www.acmicpc.net/problem/1749
 */
public class BOJ_1749 {

    static int N, M;
    static int[][] matrix, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N + 1][M + 1];
        sum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //2차원 구간합 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + matrix[i][j];
            }
        }

        int max = Integer.MIN_VALUE;
        //(1, 1)부터 (i, j)까지의 부분 행렬 중 최대 합 탐색
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                max = Math.max(max, getSum(i, j));
            }
        }

        System.out.println(max);
    }

    private static int getSum(int r, int c) {
        int max = Integer.MIN_VALUE;
        //(i, j) 부터 (r, c)까지 부분 행렬 중 최대 합 계산
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                max = Math.max(max, sum[r][c] - sum[r][j - 1] - sum[i - 1][c] + sum[i - 1][j - 1]);
            }
        }

        return max;
    }
}
