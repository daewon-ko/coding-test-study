package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_1260 {
    static int[][] graph;
    static int n;
    static int m;
    static List<Integer> selected;
    static List<Integer> selected2;
    static int v;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        graph = new int[n+1][n+1];
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
        selected = new ArrayList<>();
        selected2 = new ArrayList<>();
        selected.add(v);
        selected2.add(v);
        dfs(v, 1, new boolean[n+1]);
        bfs();
        for(int s: selected){
            System.out.print(s+" ");
        }
        System.out.println();
        for(int s: selected2){
            System.out.print(s+" ");
        }
    }
    public static void dfs(int v1, int cnt, boolean[] visited){
        visited[v] = true;
        boolean canvisit = false;
        for(int i = 1; i<=n; i++){
            if(graph[v1][i]==1 && !visited[i]){
                canvisit = true;
            }
        }
        if(!canvisit) return;
        else{
            for(int i = 1; i<=n; i++){
                if(graph[v1][i]==1 && !visited[i]){
                    selected.add(i);
                    visited[i] = true;
                    dfs(i, cnt+1, visited);
                }
            }
        } 
    }
    public static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        boolean[] visited = new boolean[n+1];
        visited[v] = true;
        while(!q.isEmpty()){
            int node = q.poll();
            for(int i = 1; i<=n; i++){
                if(graph[node][i]==1 && !visited[i]){
                    q.add(i);
                    selected2.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
