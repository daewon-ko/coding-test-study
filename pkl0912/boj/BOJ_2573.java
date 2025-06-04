package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_2573 {
    static int n;
    static int m;
    static int[][] graph;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        int x = 0;
        int y = 0;
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j]!=0){
                    x = i;
                    y = j;
                }
            }
        }
        int answer = 0;

        while (true) {
            visited = new boolean[n][m];
            int count = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (graph[i][j] > 0 && !visited[i][j]) {
                        bfs(i, j);
                        count++;
                    }
                }
            }

            if (count == 0) {
                answer = 0;
                break;
            }

            if (count >= 2) {
                break;
            }
            melt();
            answer++;
        }

        System.out.println(answer);
    }
    static void bfs(int a, int b){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            visited[x][y] = true;
            for(int i = 0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(0<=nx && nx<n && 0<=ny && ny<m){
                    if(graph[nx][ny]>0 && !visited[nx][ny]){
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
    static void melt() {
        int[][] minus = new int[n][m];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (graph[x][y] > 0) {
                    int sea = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                            if (graph[nx][ny] == 0) sea++;
                        }
                    }
                    minus[x][y] = sea;
                }
            }
        }

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                graph[x][y] -= minus[x][y];
                if (graph[x][y] < 0) graph[x][y] = 0;
            }
        }
    }
    
}
