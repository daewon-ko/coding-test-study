package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_2252 {
    static int n;
    static int[] rank;
    static List<Integer>[] graph;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        rank = new int[n+1];
        sb = new StringBuilder();
        for(int i = 0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            rank[b]++;
        }
        topo();
        System.out.println(sb);
        
    }
    static void topo(){
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i<n+1; i++){
            if(rank[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur+" ");
            for(int next: graph[cur]){
                rank[next]--;
                if(rank[next]==0){
                    q.add(next);
                }
            }
        }
    }
}
