package pkl0912.pgs;

import java.util.*;
public class PGS_388354{
    Set<Integer> set = new HashSet<>();
    Map<Integer, List<Integer>> graph;
    int forward = 0, reverse = 0;
    
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[]{0, 0};
        
        graph = new HashMap<>();
        for (int node : nodes) {
            graph.put(node, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }   

        for (int node : nodes) {
            if (set.contains(node)) continue;
            
            forward = 0;
            reverse = 0;
            bfs(node);
            if (forward == 1) {
                answer[1]++;
            }
            if (reverse == 1) {
                answer[0]++;
            }
        }
        return answer;
    }
    
    private void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        
        set.add(start);
        q.add(start);
        while (!q.isEmpty()) {
            int now = q.poll();
            
            int nowCnt = graph.get(now).size() - 1;
            if (now % 2 == 0) {
                if (nowCnt % 2 == 0) forward++;
                else reverse++;
            }
            else {
                if (nowCnt % 2 == 1) forward++;
                else reverse++;
            }
            
            List<Integer> kids = graph.get(now);
            for (int kid : kids) {
                if (set.contains(kid)) continue;
                set.add(kid);
                q.add(kid);
                
            }
        }
    }
}
