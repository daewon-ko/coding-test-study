package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_2412 {
    static int[][] board;
    static int n;
    static int t;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new int[n][2];
        visited = new boolean[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(board, Comparator.comparingInt(o->o[1]));
        System.out.println(bfs());
        
    }
    static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0, 0});
        while(!q.isEmpty()){
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];
            int d = pos[2];
            if(y==t){
                return d;
            }
            for(int i = 0; i<n; i++){
                int nx = board[i][0];
                int ny = board[i][1];
                if(visited[i]) continue;
                if(!visited[i] && Math.abs(nx-x)<=2 && Math.abs(ny-y)<=2){
                    q.add(new int[]{nx, ny, d+1});
                    visited[i] = true;
                }
                else if(ny-y>2) break;
            }
        }
        return -1;

    }
}
