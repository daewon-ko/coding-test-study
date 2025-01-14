package pkl0912.BOJ;
import java.util.*;
import java.io.*;

public class BOJ_14502 {
    static int n;
    static int m;
    static int[][] graph;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static List<int[]> emptySpaces;
    static List<int[]> viruses;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        emptySpaces = new ArrayList<>();
        viruses = new ArrayList<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                int num = Integer.parseInt(st.nextToken());
                graph[i][j] = num;
                if(num==0) emptySpaces.add(new int[]{i,j});
                if(num==2) viruses.add(new int[]{i,j});
            }
        }
        dfs(0,0, new HashSet<>());
        System.out.println(max);

    }
    public static void dfs(int start, int cnt, Set<int[]>selected){
        if(cnt == 3){
            int[][] board = new int[n][m];
            for(int i = 0; i<n; i++){
                    System.arraycopy(graph[i], 0, board[i], 0, m);
            }
            for(int[]pos: selected){
                int x =pos[0];
                int y = pos[1];
                board[x][y] = 1;
            }
            bfs(board);
            int count = 0;
            for(int i = 0; i<n; i++){
                for(int j = 0; j<m; j++){
                    if(board[i][j]==0){
                        count++;
                    }
                }
            }
            max = Math.max(max, count);
            return;
        }
        for(int i = start; i<emptySpaces.size(); i++){
            int[] pos = emptySpaces.get(i);
            selected.add(pos);
            dfs(i+1, cnt+1, selected);
            selected.remove(pos);
        }
    }
    public static void bfs(int[][] board){
        Queue<int[]> q = new LinkedList<>();
        for(int[] virus: viruses){
            q.add(virus);
        }
        while(!q.isEmpty()){
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];
            for(int i = 0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(0<=nx && nx<n && 0<=ny && ny<m){
                    if(board[nx][ny]==0){
                        board[nx][ny] = 2;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
