package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_2758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int testCase=0; testCase<t; testCase++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            long[][] dp = new long[n][m+1];
            for(int i = 1; i<=m; i++){
                dp[0][i] = 1;
            }
            for(int i = 1; i<n; i++){
                for(int j = 1; j<=m; j++){
                    for(int k = j*2; k<=m; k++){
                        dp[i][k] += dp[i-1][j];
                    }
                }
            }
            int answer = 0;
            for(int i = 1; i<=m; i++){
                answer+=dp[n-1][i];
            }
            System.out.println(answer);
        }
        
    }
}
