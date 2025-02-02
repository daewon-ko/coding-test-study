package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 알고스팟
public class BOJ_1261 {
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int n, m;
    static int[][] graph;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                graph[i][j] = line.charAt(j - 1);
            }
        }

        visited = new boolean[n + 1][m + 1];
        System.out.println(bfs(1, 1));



    }

    public static int bfs(int y, int x) {
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.offer(new Point(y, x, 0));
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            if (poll.y == n && poll.x == m) {
                return poll.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int newY = poll.y + dy[i];
                int newX = poll.x + dx[i];

                if (!inRange(newY, newX)) {
                    continue;
                }

                if (!visited[newY][newX] && graph[newY][newX] == 0) {
                    visited[newY][newX] = true;
                    queue.offer(new Point(newY, newX, poll.cnt));
                }

                if (!visited[newY][newX] && graph[newY][newX] == 1) {
                    visited[newY][newX] = true;
                    queue.offer(new Point(newY, newX, poll.cnt + 1));
                }
            }
        }

        return 0;

    }

    public static boolean inRange(int y, int x) {
        return y >= 1 && y <= n && x >= 1 && x <= m;
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int cnt; // 벽을 부순 개수

        public Point(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return cnt - o.cnt; // cnt를 기준으로 오름ㅊ순
        }
    }
}
