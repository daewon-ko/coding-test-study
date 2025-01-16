package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_1389 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n+1][n+1];
        int INF = Integer.MAX_VALUE /2 ;
        for(int i = 1; i<=n; i++){
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
        for(int k = 1; k<=n; k++){
            for(int i = 1; i<=n; i++){
                for(int j = 1; j<=n; j++){
                    if(i!= j && graph[i][k]>0 && graph[k][j]>0){
                        graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);                  
                    }
                }
            }
        }

        int min_num = 0;
        for(int i =1; i<=n; i++){
            int sum = 0;
            for(int j = 1; j<=n; j++){
                sum+= graph[i][j];
            }
            if(min > sum){
                min_num = i;
                min = sum;
            }
        }
        System.out.println(min_num);
        
    }
}
