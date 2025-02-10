package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_12865 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] weigths = new int[n+1];
        int[] values = new int[n+1];
        int[][] dp = new int[n+1][k+1];
        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            weigths[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());          
        }
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=k; j++){
                dp[i][j] = dp[i-1][j];
                if(j-weigths[i]>=0){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-weigths[i]]+values[i]);
                }
            }
        }
        System.out.println(dp[n][k]);
;
        
    }
}
