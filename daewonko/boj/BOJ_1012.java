package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 유기농 배추
public class BOJ_1012 {
    static int t;
    static int m, n, k;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());


        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            graph = new int[m][n];
            visited = new boolean[m][n];

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x][y] = 1;
            }

            int cnt = 0;

            for(int j = 0; j < m; j++) {
                for(int l = 0; l < n; l++) {
                    if(graph[j][l] == 1 && !visited[j][l]) {
                        bfs(j, l);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);

        }

    }

    public static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{y, x});
        visited[y][x] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int pollY = poll[0];
            int pollX = poll[1];

            for (int i = 0; i < 4; i++) {
                int newY = pollY + dy[i];
                int newX = pollX + dx[i];

                if (inRange(newY, newX) && !visited[newY][newX] && graph[newY][newX] == 1) {
                    visited[newY][newX] = true;
                    queue.offer(new int[]{newY, newX});
                }
            }


        }
    }

    public static boolean inRange(int y, int x) {
        return y>=0 && y<m && x>=0 && x<n;
    }


}
