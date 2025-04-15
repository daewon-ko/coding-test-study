package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_16954 {
    public static char[][] map = new char[8][8];

    public static int[] dy = {-1, 0, 1, 0, 0, -1, 1, -1, 1};
    public static int[] dx = {0, -1, 0, 1, 0, 1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs() ? 1 : 0);
    }

    public static boolean bfs() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visit;

        queue.add(new Node(7, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();

            visit = new boolean[8][8];

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                if (map[cur.x][cur.y] == '#') continue;

                if (cur.x == 0 && cur.y == 7) {
                    return true;
                }

                for (int k = 0; k < 9; k++) {
                    int nx = cur.x + dx[k];
                    int ny = cur.y + dy[k];

                    if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8) continue;
                    if (visit[nx][ny] || map[nx][ny] == '#') continue;

                    queue.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                }
            }

            wall_move();
        }

        return false;
    }

    public static void wall_move() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == '#') {
                    map[i][j] = '.';

                    if (i != 7) {
                        map[i + 1][j] = '#';
                    }
                }
            }
        }
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
