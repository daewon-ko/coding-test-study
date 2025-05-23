package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_13023 {
    static int n;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n];

        for(int i = 0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        boolean allFriend = false;
        for(int i = 0; i<n; i++){
            if(dfs(i, new boolean[n], 0)){
                allFriend = true;
            }
        }
        if(allFriend) System.out.println(1);
        else System.out.println(0);
        
        
    }
    static boolean dfs(int cur, boolean[]visited, int depth){
        visited[cur] = true;
        if(depth==4) return true;

        for(int next: graph[cur]){
            if(!visited[next]){
                if(dfs(next, visited, depth+1)){
                    return true;
                }else{
                    visited[next] = false;
                }
            }
        }
        return false;

    }
}
