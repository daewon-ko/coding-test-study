package yeonjy.boj;

import java.io.*;
import java.util.*;

public class BOJ_11049 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(bf.readLine());

		int[][] matrix = new int[n][n];
		int[][] dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] str = bf.readLine().split(" ");

			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);

			matrix[i][0] = a;
			matrix[i][1] = b;
		}

		for (int i = 0; i < n - 1; i++) {
			dp[i][i + 1] = matrix[i][0] * matrix[i][1] * matrix[i + 1][1];
		}

		for (int next = 2; next < n; next++) {
			for (int i = 0; i + next < n; i++) {
				int j = i + next;
				dp[i][j] = Integer.MAX_VALUE;

				for (int k = i; k < j; k++) {
					dp[i][j] = Math.min(dp[i][j],
							dp[i][k] + dp[k + 1][j] + (matrix[i][0] * matrix[k][1] * matrix[j][1]));
				}
			}
		}
		bw.write(dp[0][n - 1] + "\n");

		bw.flush();
		bw.close();

	}

}
