package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_15684 {
    static int n;
    static int m;
    static int h;
    static int[][] map;
    static int min = 4;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h+2][n+2];
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
        }
        for(int i = 0; i<=3; i++){
            dfs(0,i,1);
        }
        System.out.println(-1);
        
    }
    public static boolean isPossible(){
        for(int i = 1; i<=n; i++){
            int line = i;
            int col = 1;
            while(true){
                if(col==h+1){
                    break;
                }
                if(map[col][line]==1){
                    line++;
                }else if(map[col][line-1]==1){
                    line--;
                }
                col++;
            }
            if(line!=i){
                return false;
            }
        }
        return true;

    }
    public static void dfs(int level, int num, int col){
        if(level>num || level>min){
            return;
        }
        if(isPossible()){
            min = Math.min(min, level);
            System.out.println(min);
            System.exit(0);
            return;
        }
        for(int i = col; i<=h; i++){
            for(int j = 1; j<=n; j++){
                if(map[i][j]==0 && map[i][j-1]==0 && map[i][j+1]==0){
                    map[i][j] = 1;
                    dfs(level+1, num, i);
                    map[i][j] = 0;
                }
            }
        }

    }
    
}
