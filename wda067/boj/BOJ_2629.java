import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 양팔저울 / 골드3
https://www.acmicpc.net/problem/2629
 */
public class BOJ_2629 {

    private static final int MAX_WEIGHT = 40_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //추의 개수
        int[] weights = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        //dp[i][j]: i번째 추까지 사용하여 무게(구슬) j 측정 가능 여부
        boolean[][] dp = new boolean[N + 1][MAX_WEIGHT];
        dp[0][0] = true;  //무게 0은 항상 측정 가능

        for (int i = 1; i <= N; i++) {  //모든 추를 반복
            for (int bead = 0; bead < MAX_WEIGHT; bead++) {  //모든 구슬 탐색
                if (dp[i - 1][bead]) {  //이전 추까지 사용해서 현재 구슬을 측정할 수 있을 경우
                    int curW = weights[i];

                    dp[i][bead] = true;  //현재 추를 사용하지 않는 경우

                    if (bead + curW <= MAX_WEIGHT) {  //현재 추를 사용하는 경우
                        dp[i][bead + curW] = true;
                    }

                    int diff = Math.abs(bead - curW);  //현재 추를 반대쪽에 사용하는 경우
                    dp[i][diff] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());  //구슬 개수
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int bead = Integer.parseInt(st.nextToken());

            if (dp[N][bead]) {  //현재 구슬 측정 가능
                sb.append("Y");
            } else {  //현재 구슬 측정 불가능
                sb.append("N");
            }
            sb.append(" ");
        }

        System.out.println(sb);
    }
}
