package pkl0912.boj;
import java.io.*;
import java.util.*;

public class BOJ_21610 {
    static int n;
    static int[][] graph;
    static int[] dx = {0,-1,-1,-1,0,1,1,1};
    static int[] dy = {-1,-1,0,1,1,1, 0,-1};
    static int[] tx = {-1,-1,1,1};
    static int[] ty = {-1,1,1,-1};
    static int cloudcnt;
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        for(int i = 0; i<n; i++){   
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        q.add(new int[]{n-1, 0});
        q.add(new int[]{n-1, 1});
        q.add(new int[]{n-2, 0});
        q.add(new int[]{n-2, 1});
        cloudcnt = 4;
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            moveAndRainWaterCopy(d, s);

        }
        int answer = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                answer+=graph[i][j];
            }
        }
        System.out.println(answer);

        
    }
    static void moveAndRainWaterCopy(int dir, int cnt){
        int[][] board = new int[n][n];
        for(int j = 0; j<n; j++){
            System.arraycopy(graph[j], 0, board[j], 0, n);
        }
        boolean[][] visited = new boolean[n][n];
        for(int i = 0; i<cloudcnt; i++){
            int[]pos = q.poll();
            int x = pos[0];
            int y = pos[1];
            int nx = x+dx[dir]*cnt;
            int ny = y+dy[dir]*cnt;
            while(nx<0){
                nx = n+nx;
            }
            while(ny<0){
                ny = n+ny;
            }
            nx = nx%n;
            ny = ny%n;
            q.add(new int[]{nx, ny}); //움직인 좌표
            graph[nx][ny]+=1;
            board[nx][ny]+=1;
            
            
        }
        for(int i = 0; i<cloudcnt; i++){
            int[] pos = q.poll();
            int nx = pos[0];
            int ny = pos[1];
            for(int j = 0; j<4; j++){
                int tnx = nx+tx[j];
                int tny = ny+ty[j];
                if(0<=tnx && tnx<n && 0<=tny && tny<n && graph[tnx][tny]>0){
                    board[nx][ny]+=1;
                    
                }    
            }
            visited[pos[0]][pos[1]] = true;
        }

        cloudcnt = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(graph[i][j]>=2 && !visited[i][j]){
                    q.add(new int[]{i, j});
                    cloudcnt++;
                    board[i][j]-=2;
                
                }
            }
        }
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                graph[i][j] = board[i][j];
            }
        }

    }
}
