package yeonjy.boj;

import java.io.*;
import java.util.*;

public class BOJ_2758 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());

        for (int x = 0; x < test_case; x++) {
            int lotto_num,lotto_max;

            StringTokenizer st = new StringTokenizer(br.readLine());

            lotto_num = Integer.parseInt(st.nextToken());
            lotto_max = Integer.parseInt(st.nextToken());

            long[][] dp = new long[lotto_num + 1][lotto_max + 1];

            for (int k = 0; k <= lotto_max; k++) {
                dp[0][k] = 1L;
            }

            for (int y = 1; y <= lotto_num; y++) {

                for (int z = 1; z <= lotto_max; z++) {
                    dp[y][z] = dp[y - 1][z / 2] + dp[y][z - 1];
                }
            }

            bw.write(String.valueOf(dp[lotto_num][lotto_max]) + "\n");
        }
        bw.flush();
    }
}
