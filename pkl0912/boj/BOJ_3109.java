package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_3109 {
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1,1};
    static int r;
    static int c;
    static char[][] board;
    static boolean[][] visited;
    static int cnt = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        visited = new boolean[r][c];
        for(int i = 0; i<r; i++){
            String s = br.readLine();
            for(int j = 0; j<c; j++){
                board[i][j] = s.charAt(j);
            }
        }
        for(int i = 0; i<r; i++){
            if(dfs(i, 0)){
                cnt++;
            }
        }
        System.out.println(cnt);
        
    }
    static boolean dfs(int x, int y){
        visited[x][y] = true;
        if(y==c-1){
            return true;
        }
        for(int i = 0; i<3; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<r && 0<=ny && ny<c && !visited[nx][ny] && board[nx][ny]=='.'){
                if(dfs(nx,ny)){
                    return true;
                }
            }
        }
        return false;
    }  
}
