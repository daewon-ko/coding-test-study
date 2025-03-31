package ygeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1749 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 1][M + 1];
        // 누적합 구하기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + num;
            }
        }

        // 각 구간 탐색
        int max = arr[1][1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int r = 0; r < i; r++) {
                    for (int c = 0; c < j; c++) {
                        max = Math.max(max, arr[i][j] - arr[r][j] - arr[i][c] + arr[r][c]);
                    }
                }
            }
        }
        System.out.println(max);
    }
}
