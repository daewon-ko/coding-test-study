package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_11049{
    static int INF;
    static int[][] dp;
    static int[] data;
    public static void main(String[] args) throws IOException{
        INF = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        data = new int[n+1];
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            data[i] = a;
            data[i+1] = b;
        }
        dp = new int[n][n];
        for(int i  = 0; i<n; i++){
            Arrays.fill(dp[i], INF );
        }
        System.out.println(solve(0,n-1));

    }
    static int solve(int pos, int cur){
        if(pos==cur) return 0;
        if(dp[pos][cur]!=INF) return dp[pos][cur];
        for(int i = pos; i<cur; i++){
            int value = solve(pos, i) + solve(i+1, cur) + (data[pos] * data[i+1]*data[cur+1]);
            dp[pos][cur] = Math.min(value, dp[pos][cur]);
        }
        return dp[pos][cur];
    }

}