import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 자두나무 / 골드5
https://www.acmicpc.net/problem/2240
 */
public class BOJ_2240 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] trees = new int[T + 1];

        for (int i = 1; i <= T; i++) {
            trees[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[T + 1][W + 1];  //dp[t][w]: t초까지 w번 이동했을 때 얻은 자두의 최대 개수

        for (int t = 1; t <= T; t++) {
            for (int w = 0; w <= W; w++) {
                int now = (w % 2 == 0) ? 1 : 2;  //현재 위치 (w가 홀수면 2번 나무)

                int stay = dp[t - 1][w];  //현재 위치에 그대로 있는 경우
                if (trees[t] == now) {  //현재 위치에서 자두를 받을 수 있으면 +1
                    stay++;
                }

                if (w > 0) {  //이동 했을 경우
                    int move = dp[t - 1][w - 1];  //위치를 변경한 경우
                    if (trees[t] == now) {  //현재 위치에서 자두를 받을 수 있으면 +1
                        move++;
                    }

                    //현재 위치에 그대로 있는 경우와 위치를 변경한 경우를 비교
                    dp[t][w] = Math.max(stay, move);
                } else {
                    dp[t][w] = stay;
                }
            }
        }

        Arrays.stream(dp[T])
                .max()
                .ifPresent(System.out::println);
    }
}
