package pkl0912.pgs;

import java.util.*;
class PGS_150365 {
    static int[] dx = {1,0,0,-1};
    static int[] dy = {0,-1,1,0};
    static char[] dir = {'d', 'l', 'r', 'u'};
    static String answer = "";
    static boolean found = false;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int distance = Math.abs(r-x)+Math.abs(c-y);
        if(distance>k || (k-distance)%2!=0){
            return "impossible";
        }
        dfs(x, y, n, m, r, c, k, new StringBuilder());
        return answer;
    }
    static void dfs(int x, int y, int n, int m, int r, int c, int k, StringBuilder sb){
        if(found) return;
        if((Math.abs(x-r)+Math.abs(y-c))>k) return;
        if(k==0){
            if(x==r && y==c){
                answer = sb.toString();
                found = true;
            }
            return;
        }
        for(int i = 0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(1<=nx && nx<=n && 1<=ny && ny<=m){
                sb.append(dir[i]);
                dfs(nx, ny, n, m, r, c,k-1, sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}
