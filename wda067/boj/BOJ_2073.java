import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 수도배관공사 / 골드4
https://www.acmicpc.net/problem/2073
 */
public class BOJ_2073 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());  //100,000
        int P = Integer.parseInt(st.nextToken());  //350
        int[] length = new int[P];
        int[] capacity = new int[P];

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            length[i] = L;
            capacity[i] = C;
        }

        int[] dp = new int[D + 1];  //dp[i]: 길이가 i일 때 최대 용량
        dp[0] = Integer.MAX_VALUE;

        for (int i = 0; i < P; i++) {  //모든 파이프에 대해 탐색
            int len = length[i];
            for (int j = D; j >= len; j--) {
                if (dp[j - len] > 0) {  //현재 파이프로 j를 만들 수 있다면
                    int next = Math.min(dp[j - len], capacity[i]);  //사용한 파이프의 최소 용량
                    dp[j] = Math.max(dp[j], next);
                }
            }
        }

        System.out.println(dp[D]);
    }
}
