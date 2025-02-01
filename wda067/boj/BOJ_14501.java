package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 퇴사 / 실버3
https://www.acmicpc.net/problem/14501
 */
public class BOJ_14501 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //N + 1: 퇴사일
        int[] dp = new int[N + 2];  //dp[i]: i일까지 최대 금액
        int[] times = new int[N + 1];
        int[] payments = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            times[i] = T;
            payments[i] = P;
        }

        for (int i = 1; i <= N; i++) {
            int endDate = i + times[i];
            //상담을 퇴사일까지 끝낼 수 있을 때
            if (endDate <= N + 1) {
                dp[endDate] = Math.max(dp[endDate], dp[i] + payments[i]);
            }

            //상담을 하지 않는 날에도 수입은 유지
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        System.out.println(dp[N + 1]);
    }
}
