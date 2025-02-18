package daewonko.boj;

import java.io.*;
import java.util.*;
public class BOJ_11049 {


        static int[][] matrix, dp;
        static int n;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            matrix = new int[n][2];
            dp = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                matrix[i][0] = Integer.parseInt(st.nextToken());
                matrix[i][1] = Integer.parseInt(st.nextToken());
            }

            for (int len = 1; len < n; len++) {
                for (int i = 0; i + len < n; i++) {
                    int j = i + len;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1]);
                    }
                }
            }
            System.out.println(dp[0][n - 1]);
        }
    }

