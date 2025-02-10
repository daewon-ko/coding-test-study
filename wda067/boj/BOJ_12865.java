import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 평범한 배낭 / 골드5
https://www.acmicpc.net/problem/12865
 */
public class BOJ_12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] weights = new int[N];
        int[] values = new int[N];
        int[] dp = new int[K + 1];  //dp[i]: 무게 i일 때 최대 가치

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            weights[i] = W;
            values[i] = V;
        }

        for (int i = 0; i < N; i++) {  //모든 물건 탐색
            int weight = weights[i];
            int value = values[i];
            for (int j = K; j >= weight; j--) {  //현재 물건을 담을 수 있으면 갱신
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }

        System.out.println(dp[K]);
    }
}