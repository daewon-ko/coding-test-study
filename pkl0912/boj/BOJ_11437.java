package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_11437 {
    static List<Integer>[] tree;
    static int[] parent;
    static int[] depth;
    static boolean[] visited;
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        depth = new int[n+1];
        visited = new boolean[n+1];
        tree = new ArrayList[n+1];

        
        for(int i= 0; i<n+1; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        dfs(1,0);

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(lca(a,b));
        }
    }
    public static void dfs(int current, int d){
        depth[current] = d;
        visited[current] = true;
        for(int next: tree[current]){
            if(!visited[next]){
                parent[next] = current;
                dfs(next, d+1);
            }
        }
    }

    public static int lca(int a, int b){
        while(depth[a]> depth[b]){
            a = parent[a];
        }
        while(depth[b]>depth[a]){
            b = parent[b];
        }
        while(a!= b){
            a = parent[a];
            b = parent[b];
        }
        return a;
    }

}
