package pkl0912.boj;
import java.io.*;
import java.util.*;

public class BOJ_17836 {
    static int n, m, t;
    static int[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs();
        if (result == -1 || result > t) System.out.println("Fail");
        else System.out.println(result);
    }

    static int bfs() {
        boolean[][][] visited = new boolean[n][m][2]; // 0: 검 없음, 1: 검 있음
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0, 0}); // x, y, dist, isGram
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2], isGram = cur[3];

            if (x == n - 1 && y == m - 1) return dist;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny][isGram]) continue;

                if (graph[nx][ny] == 1 && isGram == 0) continue; // 벽인데 검 없음

                if (graph[nx][ny] == 2) {
                    visited[nx][ny][1] = true;
                    q.add(new int[]{nx, ny, dist + 1, 1});
                } else {
                    visited[nx][ny][isGram] = true;
                    q.add(new int[]{nx, ny, dist + 1, isGram});
                }
            }
        }

        return -1;
    }
}
