import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 가장 큰 정사각형 / 골드4
https://www.acmicpc.net/problem/1915
 */
public class BOJ_1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //dp[i][j]: (i, j)를 오른쪽 아래 꼭짓점으로 하는 정사각형의 한 변의 길이
        int[][] dp = new int[n + 1][m + 1];
        int[][] arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                arr[i][j] = charArray[j - 1] - '0';
            }
        }

        int maxSide = 0;  //가장 큰 정사각형의 한 변의 길이
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j] == 1) {  //현재 원소가 1일 경우
                    int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = Math.min(min, dp[i - 1][j - 1]) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        System.out.println(maxSide * maxSide);
    }
}
