package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_16973 {
    static int n;
    static int m;
    static int r;
    static int c;
    static int[][] graph;
    static int[][] prefixSum;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        prefixSum = new int[n+1][m+1];
        visited = new boolean[n][m];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                prefixSum[i+1][j+1] = graph[i][j] + prefixSum[i+1][j] + prefixSum[i][j+1] -prefixSum[i][j];
            }
        }
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int ex = Integer.parseInt(st.nextToken());
        int ey = Integer.parseInt(st.nextToken());
        System.out.println(bfs(sx-1, sy-1, ex-1, ey-1));

        
    }
    static int bfs(int sx, int sy, int ex, int ey){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, 0});
        while(!q.isEmpty()){
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];
            int cnt = pos[2];
            if(x==ex && y==ey){
                return cnt;
            }
            for(int i = 0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(isValid(nx, ny)&& !visited[nx][ny]){
                    q.add(new int[]{nx, ny, cnt+1});
                    visited[nx][ny] = true;
                    
                }
                
            }
        }
        return -1;
    }
    static boolean isValid(int x, int y){
        int ex = x + r - 1;
        int ey = y + c - 1;
        if(x<0 || y <0 || ex>=n || ey>=m) return false;
        int sum = prefixSum[ex+1][ey+1] - prefixSum[x][ey+1] - prefixSum[ex+1][y] + prefixSum[x][y];
        
        return sum==0;
    }
}
