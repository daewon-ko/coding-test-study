package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 내리막길
public class BOJ_1520 {
    static int m, n;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(1, 1));

    }

    public static int dfs(int y, int x) {
        if (y == m && x == n){
            return 1;
        }

        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0; // 현재 위치에서 끝점까지 도달하는 경로의 개수를 0으로 초기화.
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX < 1 || newY < 1 || newX > m || newY > n) {
                continue;
            }

            // arr[x][y]보다 arr[dx][dy]가 높이가 더 낮다면
            // arr[dx][dy]에서 끝점까지 도달하는 경로의 개수를 더한다.
            if (arr[newY][newX] > arr[newY][newX]) {
                dp[newY][newX] += dfs(newY, newX);
            }
        }


        return dp[y][x];
    }
}
