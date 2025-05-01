package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_1240 {
    static List<int[]>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
            
        }
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            boolean[]visited = new boolean[n+1];
            int dist = dfs(start, end, 0, visited);
            System.out.println(dist);
        }
        
        
    }
    static int dfs(int node, int end, int sum, boolean[] visited){
        if(node==end){
            return sum;
        }
        visited[node] = true;
        for(int[] next: graph[node]){
            if(!visited[next[0]]){
                int result = dfs(next[0], end, sum+next[1], visited);
                if(result!=-1) return result;
            }
        }
        return -1;
    }
}
