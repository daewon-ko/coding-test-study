package pkl0912.BOJ;
import java.util.*;
import java.io.*;
public class BOJ_1012{
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] graph;
    static ArrayList<int[]> arr;
    static int n;
    static int m;
    static int k;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            graph = new int[n][m];
            answer = 0;
            for(int j = 0; j<k; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[b][a] = 1;
            }
            for(int x = 0; x<n; x++){
                for(int y = 0; y<m; y++){
                    if(graph[x][y]==1){
                        dfs(x, y);
                        answer++;
                    }
                }
            }
            System.out.println(answer);

        }
        
    }
    public static void dfs(int x, int y){
   
        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0<=nx && nx < n &&0<=ny && ny<m && graph[nx][ny]==1){    
                graph[nx][ny] = 0;
                dfs(nx, ny);
            }
        }
    }
    

}