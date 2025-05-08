package pkl0912.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
import java.util.*;

public class BOJ_2073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int[] len = new int[p];
        int[] cap = new int[p];
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            len[i] = Integer.parseInt(st.nextToken());
            cap[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[d + 1];
        Arrays.fill(dp, 0);
        dp[0] = Integer.MAX_VALUE; 

        for (int i = 0; i < p; i++) {
            for (int j = d; j >= len[i]; j--) {
                if (dp[j - len[i]] > 0) {
                    dp[j] = Math.max(dp[j], Math.min(dp[j - len[i]], cap[i]));
                }
            }
        }

        System.out.println(dp[d]);
    }
}
