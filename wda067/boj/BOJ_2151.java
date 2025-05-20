import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
백준 / 거울 설치 / 골드3
https://www.acmicpc.net/problem/2151
 */
public class BOJ_2151 {

    private static final int INF = Integer.MAX_VALUE;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static int n;
    private static char[][] map;
    private static int[][][] mirror;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        mirror = new int[n][n][4];

        List<int[]> doors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '#') {
                    doors.add(new int[]{i, j});
                }
                for (int d = 0; d < 4; d++) {
                    mirror[i][j][d] = INF;
                }
            }
        }

        int[] start = doors.get(0);
        int[] end = doors.get(1);
        dijkstra(start[0], start[1], end[0], end[1]);
    }

    private static void dijkstra(int sx, int sy, int ex, int ey) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.count));

        for (int d = 0; d < 4; d++) {
            int nx = sx + dx[d];
            int ny = sy + dy[d];
            if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] != '*') {
                pq.offer(new Node(nx, ny, d, 0));
                mirror[nx][ny][d] = 0;
            }
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.x, y = cur.y, dir = cur.dir, cnt = cur.count;

            if (x == ex && y == ey) {
                System.out.println(cnt);
                return;
            }

            //직진
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] != '*') {
                if (mirror[nx][ny][dir] > cnt) {
                    mirror[nx][ny][dir] = cnt;
                    pq.offer(new Node(nx, ny, dir, cnt));
                }
            }

            //거울 설치 가능 위치에서 꺾기
            if (map[x][y] == '!') {
                for (int nd = 0; nd < 4; nd++) {
                    if ((dir == 0 || dir == 1) && (nd == 2 || nd == 3) ||
                            (dir == 2 || dir == 3) && (nd == 0 || nd == 1)) {
                        int tx = x + dx[nd];
                        int ty = y + dy[nd];
                        if (0 <= tx && tx < n && 0 <= ty && ty < n && map[tx][ty] != '*') {
                            if (mirror[tx][ty][nd] > cnt + 1) {
                                mirror[tx][ty][nd] = cnt + 1;
                                pq.offer(new Node(tx, ty, nd, cnt + 1));
                            }
                        }
                    }
                }
            }
        }
    }

    private static class Node {
        private int x, y, dir, count;

        private Node(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }
    }

}
