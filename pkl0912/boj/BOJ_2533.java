package pkl0912.boj;
import java.io.*;
import java.util.*;

public class BOJ_2533{
    static List<Integer>[] graph;
    static int[][]dp;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        for(int i = 0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        dp = new int[n+1][2];
        visited = new boolean[n+1];
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
        
    }
    static void dfs(int node){
        dp[node][0] = 0;
        dp[node][1] = 1;
        visited[node] = true;
        for(int next: graph[node]){
            if(!visited[next]){
                dfs(next);
                dp[node][0] += dp[next][1];
                dp[node][1] += Math.min(dp[next][0], dp[next][1]);
            }
            
        }
    }
}