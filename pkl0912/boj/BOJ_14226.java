package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_14226 {
    static int s;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = Integer.parseInt(br.readLine());
        visited = new boolean[s+1][s+1];
        bfs();
        System.out.println(min);

    }
    static void bfs(){
        Queue<int[]>q = new LinkedList<>();
        q.add(new int[]{1,0,0});
        visited[1][0] = true;
        while(!q.isEmpty()){
            int[] pos = q.poll();
            int screen = pos[0];
            int clip = pos[1];
            int time = pos[2];
            if(screen==s){
                min = Math.min(min, time);
                return;
            }
            if(clip+screen<= s && !visited[screen][Math.max(screen, clip)]){
                q.add(new int[]{screen, Math.max(screen, clip), time+1});
                visited[screen][Math.max(screen, clip)] = true;
            }
            if(clip+screen<= s && clip!=0 && !visited[screen+clip][clip]){
                q.add(new int[]{screen+clip, clip, time+1});
                visited[screen+clip][clip] = true;
            }
            if(screen>0 && !visited[screen-1][clip]){
                q.add(new int[]{screen-1, clip, time+1});
                visited[screen-1][clip] = true;
            }
        }
    }
}
