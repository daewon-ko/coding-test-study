package pkl0912.BOJ;
import java.util.*;
import java.io.*;
public class BOJ_11660 {
    static int[][]graph;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                int num = Integer.parseInt(st.nextToken());
                if(j==0) graph[i][j] = num;
                else graph[i][j] = graph[i][j-1] + num;
            }
        }
        for(int i = 0; i<m; i++){
            st  = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken());
            int fy = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            
            int result = cal(fx-1, fy-1, sx-1, sy-1);
            System.out.println(result);
        }
    }
    public static int cal(int x1, int y1, int x2, int y2){
        int answer = 0;
        for(int i = x1; i<=x2; i++){
            if(y1==0) answer+= (graph[i][y2]);
            else answer+= (graph[i][y2] - graph[i][y1-1]);
        }
        return answer;
    }
}
