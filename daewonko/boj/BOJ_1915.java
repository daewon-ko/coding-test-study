package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 가장 큰 정사각형
public class BOJ_1915 {
    static int n, m;
    static int[][] graph;
    static int MAX = Integer.MIN_VALUE;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        graph = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }


        // 그래프의 모든 좌표점에 대해서 dfs를 수행한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = graph[i][j];
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    MAX = Math.max(MAX, dp[i][j]);
                }
            }
        }

        System.out.println(MAX * MAX);


    }

}
