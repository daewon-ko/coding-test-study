package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_1941 {
    static int answer = 0;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static char[][] graph;
    static List<int[]> positions;
    public static void main(String[] args) throws IOException{
        graph = new char[5][5];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        positions = new ArrayList<>();
        for(int i = 0; i<5; i++){
            String s = br.readLine();
            for(int j = 0; j<5; j++){
                graph[i][j] = s.charAt(j);
                positions.add(new int[]{i,j});
            }
        }
        comb(0,0,new int[7]);
        System.out.println(answer);
        
    }
    static void comb(int start, int cnt, int[] comb){
        if(cnt==7){
            if(isValid(comb)) answer++;
            return;
        }
        for(int i = start; i<25; i++){
            comb[cnt] = i;
            comb(i+1, cnt+1, comb); 
        }
    }
    static boolean isValid(int[] comb){
        boolean[] visited = new boolean[7];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;
        int cnt =1;
        int scnt = 0;
        if(graph[positions.get(comb[0])[0]][positions.get(comb[0])[1]]=='S') scnt++;
        while(!q.isEmpty()){
            int cur = q.poll();
            int x = positions.get(comb[cur])[0];
            int y = positions.get(comb[cur])[1];
            for(int i = 0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                for(int j = 0; j<7; j++){
                    if(!visited[j]){
                        int tx = positions.get(comb[j])[0];
                        int ty = positions.get(comb[j])[1];
                        if(tx==nx && ty==ny){
                            visited[j] = true;
                            q.add(j);
                            cnt++;
                            if(graph[tx][ty]=='S') scnt++;
                        }
                    }
                }
            }

        }
        return cnt==7 && scnt>=4;

    }
    
}
