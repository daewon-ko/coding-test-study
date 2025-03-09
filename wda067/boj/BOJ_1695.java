import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
백준 / 팰린드롬 만들기 / 골드3
https://www.acmicpc.net/problem/1695
 */
public class BOJ_1695 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] rev = new int[N];  //역순 배열
        int index = N - 1;
        for (int i = 0; i < N; i++) {
            rev[i] = arr[index--];
        }

        int[][] dp = new int[N + 1][N + 1];  //dp[i][j]: arr[0:i]과 rev[0:j]의 LCS 길이

        //LCS 구하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                if (arr[i - 1] == rev[j - 1]) {  //두 수가 같다면 LCS 길이 +1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {  //LCS 최대 길이는 유지된다.
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }

        System.out.println(N - dp[N][N]);
    }
}
