package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1106 {
    static int C;
    static int[][] promotions;
    static int[] employees;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int maxEmployee = 0;
        promotions = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            promotions[i][0] = Integer.parseInt(st.nextToken());
            promotions[i][1] = Integer.parseInt(st.nextToken());
            maxEmployee = Math.max(maxEmployee, promotions[i][1]);
        }

        employees = new int[C + maxEmployee + 1];

        dp(0, 0);
        System.out.println(min);
    }

    static void dp(int cnt, int money) {
        if (cnt >= C) {
            return;
        }
        for (int[] promotion : promotions) {
            int nextMoney = money + promotion[0];
            int nextCnt = cnt + promotion[1];

            if (employees[nextCnt] == 0 || employees[nextCnt] > nextMoney) {
                employees[nextCnt] = nextMoney;
                if (nextCnt >= C) {
                    min = Math.min(min, nextMoney);
                }
                dp(nextCnt, nextMoney);
            }
        }
    }
}
