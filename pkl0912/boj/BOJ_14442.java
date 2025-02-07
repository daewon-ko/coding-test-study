package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_14442 {
    static int[][] graph;
    static int[][] walls;
    static int[][] dist;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int n;
    static int m;
    static int k;

    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for(int i = 0; i<n; i++){
            String s = br.readLine();
            for(int j = 0; j<m; j++){
                graph[i][j] = s.charAt(j) - '0';     

            }
        }

        
        int result = bfs();
        
        System.out.println(result);

    }
    public static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0, 0});
        int[][][]dist = new int[n][m][k+1];
        dist[0][0][0] = 1;
        while(!q.isEmpty()){
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];
            int broken = pos[2];
            if(x==n-1 && y == m-1){
                return dist[x][y][broken];
            }

            for(int i = 0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(0<=nx && nx <n && 0<=ny && ny<m){
                    if(graph[nx][ny]==0 && dist[nx][ny][broken]==0){
                        dist[nx][ny][broken] = dist[x][y][broken]+1;
                        q.add(new int[]{nx, ny, broken});
                    }
                    if(graph[nx][ny]==1 && broken<k && dist[nx][ny][broken+1]==0 ){
                        dist[nx][ny][broken+1] = dist[x][y][broken]+1;
                        q.add(new int[]{nx, ny, broken+1});
                    }
                }
            }
        }
        return -1;
    }
    
}
