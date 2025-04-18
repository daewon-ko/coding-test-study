package pkl0912.pgs;

import java.util.*;

public class PGS_43162 {
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for(int i = 0; i<n; i++){
            if(!visited[i]){
                answer++;
                bfs(i, n, computers);
            }
        }
        return answer;
    }
    void bfs(int start, int n, int[][] computers){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            int cur = q.poll();
            visited[cur] = true;
            for(int i = 0; i<n; i++){
                int next = computers[cur][i];
                if(next==1 && !visited[i] && i!=start){
                    q.add(i);
                }
            }
        }
    }
    
}