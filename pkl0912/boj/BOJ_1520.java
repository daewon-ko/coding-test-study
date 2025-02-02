package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_1520 {
    static int[]dx = {-1,0,1,0};
    static int[]dy = {0,-1,0,1};
    static int n;
    static int m;
    static int[][] graph, dp;
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        dp = new int[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0,0));
        
    }

    static int dfs(int x, int y){
        if(x==n-1 && y==m-1){
            return 1;
        }
        if(dp[x][y]!=-1){
            return dp[x][y];
        }
        dp[x][y] = 0;
        for(int i = 0; i<4; i++){ 
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<n && 0<=ny && ny<m){
                if(graph[nx][ny]<graph[x][y]){
                    dp[x][y]+= dfs(nx, ny);
                    
                }
            }
        }
        return dp[x][y];
    }
}
