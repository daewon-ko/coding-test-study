package pkl0912.boj;
import java.io.*;
import java.util.*;

public class BOJ_16234 {
    static int n;
    static int l;
    static int r;
    static int[][] graph;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int result = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while(true){
            if(!bfs()) break;
            result++;
        }     
        System.out.println(result);


    }
    static boolean bfs(){
        boolean[][] visited = new boolean[n][n];
        boolean moved = false;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(!visited[i][j]){
                    Queue<int[]> q = new LinkedList<>();
                    int cnt = 0;
                    int sum = 0;
                    visited[i][j] = true;
                    q.add(new int[]{i,j});
                    List<int[]> unions = new ArrayList<>();
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int x = cur[0];
                        int y = cur[1];
                        unions.add(new int[]{x, y});
                        cnt++;
                        sum+=graph[x][y];

                        for(int d = 0; d<4; d++){
                            int nx = x+dx[d];
                            int ny = y+dy[d];
                            if(0<=nx && nx <n && 0<=ny && ny<n && l<=Math.abs(graph[nx][ny]-graph[x][y]) && Math.abs(graph[nx][ny]-graph[x][y])<=r && !visited[nx][ny]){
                                q.add(new int[]{nx,ny});
                                visited[nx][ny] = true;
                            }
                        }
                    }
                    if(cnt>1){
                        moved = true;
                        for(int[] p: unions){
                            graph[p[0]][p[1]] = sum/cnt;
                        }
                    }
                
                }
            }
        }
        return moved;
    }
}
