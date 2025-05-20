package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_2151 {
    static class Point {
        int x, y, dir, mirror;

        Point(int x, int y, int dir, int mirror) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirror = mirror;
        }
    }

    static int[] dx = {-1, 0, 1, 0}; 
    static int[] dy = {0, -1, 0, 1};
    static int n;
    static char[][] board;
    static boolean[][][] visited;
    static List<int[]> doors = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == '#') {
                    doors.add(new int[]{i, j});
                }
            }
        }

        bfs();
    }

    static void bfs() {
        int[] start = doors.get(0);
        int[] end = doors.get(1);

        visited = new boolean[n][n][4];
        Queue<Point> q = new LinkedList<>();

        for (int d = 0; d < 4; d++) {
            int nx = start[0] + dx[d];
            int ny = start[1] + dy[d];
            if (0 <= nx && nx < n && 0 <= ny && ny < n && board[nx][ny] != '*') {
                q.offer(new Point(nx, ny, d, 0));
                visited[nx][ny][d] = true;
            }
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == end[0] && p.y == end[1]) {
                System.out.println(p.mirror);
                return;
            }

            int nx = p.x + dx[p.dir];
            int ny = p.y + dy[p.dir];

            if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny][p.dir] && board[nx][ny] != '*') {
                visited[nx][ny][p.dir] = true;
                if (board[nx][ny] == '!') {
                    for (int i = 0; i < 4; i++) {
                        if ((p.dir + 1) % 4 == i || (p.dir + 3) % 4 == i) {
                            if (!visited[nx][ny][i]) {
                                visited[nx][ny][i] = true;
                                q.offer(new Point(nx, ny, i, p.mirror + 1));
                            }
                        }
                    }
                }

                q.offer(new Point(nx, ny, p.dir, p.mirror));
            }
        }
    }
}
