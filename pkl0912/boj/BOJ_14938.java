package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_14938 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] nodes = new int[n+1];
        st = new StringTokenizer(br.readLine());
        List<Nodes>[] graph = new ArrayList[n+1];
        for(int i = 0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i<n+1; i++){
            nodes[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Nodes(b, c));
            graph[b].add(new Nodes(a, c));
         }
        int max = 0;
        for(int i = 1; i<n+1; i++){
            PriorityQueue<Nodes> q = new PriorityQueue<>();
            boolean[] visited = new boolean[n+1];
            q.add(new Nodes(i, 0));
            int sum = 0;
            while(!q.isEmpty()){
                Nodes curNode = q.poll();
                int cur = curNode.next;
                int distance = curNode.dist;
                if(!visited[cur]){
                    visited[cur] = true;
                    sum+=nodes[cur];
                    for(Nodes nextNode: graph[cur]){
                        if(distance+nextNode.dist>m){
                        }else{
                            q.add(new Nodes(nextNode.next, distance+nextNode.dist));
                        }
                    }
                }
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);

    }
    
}
class Nodes implements Comparable<Nodes>{
    int next;
    int dist;
    Nodes(int next, int dist){
        this.next = next;
        this.dist = dist;
    }
    @Override
    public int compareTo(Nodes ob){
        return this.dist - ob.dist;
    }
}
