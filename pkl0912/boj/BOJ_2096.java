package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_2096 {
    static int[] dx = {-1,-1,-1};
    static int[] dy = {-1,0,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][3];
        int[][] dp = new int[n][3];
        int[][] dpMin = new int[n][3];
        for(int i = 0; i<n; i++){
            Arrays.fill(dpMin[i], Integer.MAX_VALUE);
        }
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i<n; i++){
            for(int j = 0; j<3; j++){
                if(i==0){
                    dp[i][j] = graph[i][j];
                    dpMin[i][j] = graph[i][j];
                }else if(i>0){
                    for(int d = 0; d<3; d++){
                        int px = i+dx[d];
                        int py = j+dy[d];
                        if(0<=px && px<n && 0<=py && py<3){
                            dp[i][j] = Math.max(dp[i][j], graph[i][j]+dp[px][py]);
                            dpMin[i][j] = Math.min(dpMin[i][j], graph[i][j]+dpMin[px][py]);
                        }
                    }
                }
            }
        }
        
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<3; i++){
            min = Math.min(min, dpMin[n-1][i]);
            max = Math.max(max, dp[n-1][i]);
        }
        System.out.println(max+" "+min);
    }
    
}
