package pkl0912.boj;
import java.io.*;
import java.util.*;

public class BOJ_1261 {
    static int[][] graph;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int n;
    static int m;
    static int[][] dist;
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        dist = new int[n][m];

        for(int i = 0; i<n; i++){
            String s = br.readLine();
            for(int j = 0; j<m; j++){
                graph[i][j] = s.charAt(j)-'0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        bfs();
        System.out.println(dist[n-1][m-1]);
        

    }
    static void bfs(){
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0});
        dist[0][0] = 0;
        while(!q.isEmpty()){
            int[] pos = q.pollFirst();
            int x = pos[0];
            int y = pos[1];
            for(int i  =0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(0<=nx && nx<n && 0<=ny && ny<m ){
                    int cost = dist[x][y] + graph[nx][ny];
                    if(cost<dist[nx][ny]){
                        dist[nx][ny] = cost;
                        if(graph[nx][ny]==1){
                            q.addLast(new int[]{nx, ny});
                        }else{
                            q.addFirst(new int[]{nx, ny});
                        }
                    }
                }
            }
        }

    }
}
