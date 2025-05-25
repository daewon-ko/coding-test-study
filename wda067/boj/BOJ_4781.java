import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 사탕 가게 / 골드4
https://www.acmicpc.net/problem/4781
 */
public class BOJ_4781 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;
        while (!(s = br.readLine()).equals("0 0.00")) {
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int m = (int) Math.round(Double.parseDouble(st.nextToken()) * 100);

            int[] dp = new int[m + 1];  //dp[i]: i원으로 구매할 수 있는 가장 높은 칼로리
            int[] calories = new int[n];
            int[] prices = new int[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int p = (int) Math.round(Double.parseDouble(st.nextToken()) * 100);

                calories[i] = c;
                prices[i] = p;
            }

            for (int i = 0; i < n; i++) {
                int c = calories[i];
                int p = prices[i];

                //0-1 Knapsack -> 내림차순
                //Unbounded Knapsack -> 오름차순
                for (int j = p; j <= m; j++) {
                    dp[j] = Math.max(dp[j], dp[j - p] + c);
                }
            }

            System.out.println(dp[m]);
        }
    }
}
