package pkl0912.boj;
import java.io.*;
import java.util.*;

public class BOJ_14501 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] dp = new int[n+1];
        int[] t = new int[n];
        int[] p = new int[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i<n; i++){
            if(i+t[i]<=n){
                dp[i+t[i]] = Math.max(dp[i+t[i]], dp[i]+p[i]);
            }
            dp[i+1] = Math.max(dp[i], dp[i+1]);
        }
        System.out.println(dp[n]);
    }
}
