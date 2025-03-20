package pkl0912.boj;
import java.io.*;
import java.util.*;

public class BOJ_17136 {
    static int[][] arr;
    static List<int[]> ones = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    static int[] papers;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr = new int[10][10];
        for(int i = 0; i<10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<10; j++){
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                if(num==1) ones.add(new int[]{i,j});
            }
        }
        papers = new int[5];
        for(int i = 0; i<5; i++){
            papers[i] = 5;
        }
        visited = new boolean[10][10];
        dfs(0,0);
        if(answer==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);   
        
    }
    static boolean canPlacePaper(int x, int y, int p){
        if(x+p>10 || y+p>10) return false;
        for(int i = 0; i<p; i++){
            for(int j = 0; j<p; j++){
                if(arr[x+i][y+j]==0 || visited[x+i][y+j]){
                    return false;
                }
            }
        }
        return true;
    }
    static void placePaper(int x, int y, int p, boolean flag){
        for(int i = 0; i<p; i++){
            for(int j = 0; j<p; j++){
                visited[x+i][y+j] = flag;
            }
        }
    }
    static void dfs(int cnt, int paperCnt){
        if(cnt==ones.size()){
            answer = Math.min(paperCnt, answer);
            return;
        }
        if(paperCnt> answer) return;
        int[] pos = ones.get(cnt);
        int x = pos[0];
        int y = pos[1];
        if(visited[x][y]) dfs(cnt+1, paperCnt);
        for(int p = 5; p>0; p--){
            if(canPlacePaper(x, y, p) && papers[p-1]>0){
                placePaper(x, y, p, true);
                papers[p-1]--;
                dfs(cnt+1, paperCnt+1);
                placePaper(x, y, p, false);
                papers[p-1]++;
            }
        }


    }
}
