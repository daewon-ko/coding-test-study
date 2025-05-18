package pkl0912.pgs;

import java.util.*;

public class PGS_49189 {
    List<List<Integer>> graph = new ArrayList<>();
    int[] dist;
    public int solution(int n, int[][] edge) {
        int answer = 0;
    
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i=0; i<edge.length; i++){
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
        }
        
        dist = new int[n+1];
        dijkstra(1, dist);
        
        Integer[] d = Arrays.stream(dist).boxed().toArray(Integer[]::new);
        Arrays.sort(d, Collections.reverseOrder());
        int max = d[0]; 
        answer++;
        for(int i=1; i<dist.length; i++){
            if(max==d[i]){
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dijkstra(int now, int[] dist){
        for(int i=1; i<dist.length; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        
        dist[now] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(now);
        
        while(!q.isEmpty()){
            int n = q.poll();
            
            for(int i=0; i<graph.get(n).size(); i++){
                int next = graph.get(n).get(i);
                if(dist[n]+1 < dist[next]){
                    dist[next] = dist[n] + 1;
                    q.add(next);
                }
            }
        }
    }
    
}

