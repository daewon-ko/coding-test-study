package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_2240 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[] arr = new int[t+1];
        for(int i = 1; i<=t; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        int answer = 0;
        int pos = 1;
        
        int[][] dp = new int[t+1][w+1];
        for(int i = 1; i<=t; i++){
            int treepos = arr[i];
            for(int j = 0; j<=w; j++){
                if(j==0){
                    pos = 1;
                    if(pos==treepos){
                        dp[i][j] = dp[i-1][j]+1;
                    }else{
                        dp[i][j] = dp[i-1][j];
                    }
                    continue;
                }
                if(j%2==0){ //위치가 1
                    pos = 1;
                    if(pos==treepos){
                        dp[i][j] = Math.max(dp[i-1][j]+1, dp[i-1][j-1]);
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]+1);
                    }
                }else{ //위치가 2
                    pos = 2;
                    if(pos==treepos){
                        dp[i][j] = Math.max(dp[i-1][j]+1, dp[i-1][j-1]);
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]+1);
                    }
                }
                answer = Math.max(answer, dp[i][j]);
            }          
        }
        System.out.println(answer);
        
    }
}
