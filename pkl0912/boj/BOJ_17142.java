package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_17142 {
    static int n;
    static int m;
    static int[][] board;
    static List<int[]> viruses;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        
        viruses = new ArrayList<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]==2) viruses.add(new int[]{i,j});
            }
        }
        dfs(0,0,new ArrayList<>(), new boolean[n][n]);
        if(min!=Integer.MAX_VALUE) System.out.println(min);
        else System.out.println(-1);
        
    }
    static void bfs(List<int[]>selected, int[][] dist){
        Queue<int[]> q = new LinkedList<>();
        for(int[] s: selected){
            q.add(s);
            dist[s[0]][s[1]] = 0;
        }
        int max = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            for(int i = 0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(0<=nx && nx<n && 0<=ny && ny<n && dist[nx][ny] == -1 && board[nx][ny]!=1){
                    dist[nx][ny] = dist[x][y] + 1;
                    q.add(new int[]{nx, ny});

                    if (board[nx][ny] == 0) {
                        max = Math.max(max, dist[nx][ny]);
                    }
                }
            }
        }
        boolean flag = true;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(dist[i][j]==-1 && board[i][j]==0) flag = false;
            }
        }
        if(flag) min = Math.min(max, min);
    }
    static void dfs(int start, int cnt, List<int[]>selected, boolean[][]visited){
        if(cnt==m){

            int[][] dist = new int[n][n];
            for(int i = 0; i<n; i++){
                Arrays.fill(dist[i], -1);
            }
            bfs(selected, dist);          
            return; 
        }
        for(int i = start; i<viruses.size(); i++){
            int x = viruses.get(i)[0];
            int y = viruses.get(i)[1];
            if(!visited[x][y]){
                visited[x][y] = true;
                selected.add(viruses.get(i));
                dfs(i+1, cnt+1, selected, visited);
                selected.remove(selected.size()-1);
                visited[x][y] = false;
            }
        }
    }    
}
