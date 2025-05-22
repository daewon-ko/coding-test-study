package pkl0912.pgs;

import java.util.*;

public class PGS_42861 {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        PriorityQueue<Node> q = new PriorityQueue<>();
        List<Node>[] graph = new ArrayList[n];
        for(int i = 0; i<n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] cost: costs){
            graph[cost[0]].add(new Node(cost[1], cost[2]));
            graph[cost[1]].add(new Node(cost[0], cost[2]));
        }
        
        int cnt = 0;
        boolean[] visited = new boolean[n];
        q.add(new Node(0, 0));
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(visited[cur.to]) continue;
            visited[cur.to] = true;
            answer+= cur.cost;
            for(Node next: graph[cur.to]){
                if(!visited[next.to]){
                    q.add(next);
                }
            }
        }
        
        return answer;
    }
}
class Node implements Comparable<Node>{
    int to;
    int cost;
    Node(int to, int cost){
        this.to = to;
        this.cost = cost;
    }
    @Override
    public int compareTo(Node ob){
        return this.cost-ob.cost;
    }
}