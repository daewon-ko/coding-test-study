package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); 
        int k = Integer.parseInt(st.nextToken()); 

        int[] dp = new int[k + 1]; 
        int[] coins = new int[n];  

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine()); 
        }

        dp[0] = 1; 

        for (int c : coins) { /
            for (int j = c; j <= k; j++) { 
                dp[j] += dp[j - c]; 
            }
        }

        System.out.println(dp[k]); 
    }
}
