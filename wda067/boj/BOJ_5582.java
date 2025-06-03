import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
백준 / 공통 부분 문자열 / 골드5
https://www.acmicpc.net/problem/5582
 */
public class BOJ_5582 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1 = br.readLine();
        String s2 = br.readLine();

        int s1Len = s1.length();
        int s2Len = s2.length();

        //dp[i][j]: s1의 i번째 문자와 s2의 j번째 문자가 같을 때까지의 연속된 LCS의 길이
        int[][] dp = new int[s1Len + 1][s2Len + 1];
        int max = 0;

        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;  //이전 LCS에 길이 1 추가
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.println(max);
    }
}
