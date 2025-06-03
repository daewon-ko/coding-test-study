package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_1987 {
    static int r;
    static int c;
    static char[][] board;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        for(int i = 0; i<r; i++){
            String s = br.readLine();
            for(int j = 0; j<c; j++){
                board[i][j] = s.charAt(j);
            }
        }
        List<Character>selected = new ArrayList<>();
        selected.add(board[0][0]);
        dfs(0,0,1,new boolean[r][c], selected);
        System.out.println(max);
        
    }
    static void dfs(int x, int y, int depth, boolean[][] visited, List<Character>selected){
        
        boolean canMove = false;
        visited[x][y] = true;
        for(int i = 0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx && nx<r && 0<=ny && ny<c && !visited[nx][ny] && !selected.contains(board[nx][ny])){
                depth++;
                visited[nx][ny] = true;
                selected.add(board[nx][ny]);
                canMove = true;
                dfs(nx, ny, depth, visited, selected);
                visited[nx][ny] = false;
                selected.remove(selected.size()-1);
                depth--;
            }
        }
        if(!canMove){
            max = Math.max(max, depth);
            return;
        }

    }
}
