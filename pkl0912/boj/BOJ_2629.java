package pkl0912.boj;
import java.io.*;
import java.util.*;

public class BOJ_2629 {
    static int n, m;
    static int[] chu, weight;
    static boolean[][] dp;
    static int MAX = 15000; // 가능한 무게 범위의 절반 (음수 포함 총 30000)
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        n = Integer.parseInt(br.readLine());
        chu = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            chu[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        weight = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        // DP 배열 초기화
        dp = new boolean[n + 1][MAX * 2 + 1]; // 음수 무게도 포함해서 0 ~ 30000 범위
        dp[0][MAX] = true; // 초기 상태 (0 무게 가능)

        // DP 수행
        for (int i = 1; i <= n; i++) {
            int w = chu[i - 1]; // 현재 추가할 추의 무게
            
            for (int j = -MAX; j <= MAX; j++) {
                if (dp[i - 1][j + MAX]) { // 이전 상태에서 가능한 무게라면
                    dp[i][j + MAX] = true; // 그대로 유지
                    dp[i][j + MAX + w] = true; // 현재 추를 오른쪽에 추가
                    dp[i][j + MAX - w] = true; // 현재 추를 왼쪽에 추가
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int w : weight) {
            if (w > MAX) sb.append("N ");
            else sb.append(dp[n][w + MAX] ? "Y " : "N ");
        }
        System.out.println(sb.toString().trim());
    }
}

