package pkl0912.pgs;
import java.util.*;

public class PGS_118669 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        List<Integer>summitList = new ArrayList<>();
        List<Integer>gateList = new ArrayList<>();
        for(int i = 0; i<summits.length; i++){
            summitList.add(summits[i]);
        }
        for(int i = 0; i<gates.length; i++){
            gateList.add(gates[i]);
        }
        List<int[]>[] graph = new ArrayList[n+1];
        for(int i = 0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i<paths.length; i++){
            int a = paths[i][0];
            int b = paths[i][1];
            int c = paths[i][2];
            
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        int[] intensity = new int[n+1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        for(int g: gates){
            pq.offer(new int[]{g, 0});
            intensity[g] = 0;
        }
        
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int cur = now[0];
            int curIntensity = now[1];
            
            if(intensity[cur]<curIntensity) continue;
            if(summitList.contains(cur)) continue;
            for(int[] pos: graph[cur]){
                int next = pos[0];
                int weight = pos[1];
                int newIntensity = Math.max(weight, curIntensity);
                if(intensity[next]>newIntensity){
                    intensity[next] = newIntensity;
                    pq.add(new int[]{next, newIntensity});
                }
            }
        }
        int minIn = Integer.MAX_VALUE;
        int minS = Integer.MAX_VALUE;
        Arrays.sort(summits);
        for(int summit:summits){
            if(intensity[summit]<minIn){
                minIn = intensity[summit];
                minS = summit;
            }
        }
        answer[0] = minS;
        answer[1] = minIn;
        return answer;
    }
}