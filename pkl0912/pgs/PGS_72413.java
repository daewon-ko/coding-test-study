package pkl0912.pgs;

import java.util.*;

public class PGS_72413{
    List<List<Node>> graph;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<fares.length; i++){
            graph.get(fares[i][0]).add(new Node(fares[i][1], fares[i][2]));
            graph.get(fares[i][1]).add(new Node(fares[i][0], fares[i][2]));
        }
        
    
        int[] distS = dijkstra(s, n);
        int[] distA = dijkstra(a, n);
        int[] distB = dijkstra(b, n);
        
        int sum = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++){
            if(sum > distS[i]+distA[i]+distB[i]){
                sum = distS[i]+distA[i]+distB[i];
            }
        }
        
        answer = sum;
        
        return answer;
    }
    
    public int[] dijkstra(int s, int n){
        int[] dist = new int[n+1];
        for(int i=0; i<=n; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[s] = 0;
        pq.add(new Node(s, 0));
        
        while(!pq.isEmpty()){
            Node u = pq.poll();
            
            if(dist[u.n] < u.cost) continue;
            
            for(int i=0; i<graph.get(u.n).size(); i++){
                Node next = graph.get(u.n).get(i);
                if(dist[u.n] + next.cost < dist[next.n]){
                    dist[next.n] = dist[u.n] + next.cost;
                    pq.add(new Node(next.n, dist[next.n]));
                }
            }
        }
        
        return dist;
    }
    
    class Node implements Comparable<Node>{
        int n, cost;
        Node(int n, int cost){
            this.n = n;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}