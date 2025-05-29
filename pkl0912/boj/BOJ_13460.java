package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_13460 {
    static int n, m;
    static char[][] graph;
    static boolean[][][][] visited;
    static int[] dx = {-1, 0, 1, 0}; // 상 좌 하 우
    static int[] dy = {0, -1, 0, 1};

    static class State {
        int rx, ry, bx, by, depth;
        State(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx; this.ry = ry;
            this.bx = bx; this.by = by;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new char[n][m];
        visited = new boolean[n][m][n][m];

        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j);
                if (graph[i][j] == 'R') {
                    rx = i; ry = j;
                } else if (graph[i][j] == 'B') {
                    bx = i; by = j;
                }
            }
        }

        Queue<State> q = new LinkedList<>();
        q.add(new State(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();
            if (cur.depth >= 10) break;

            for (int dir = 0; dir < 4; dir++) {
                int[] red = move(cur.rx, cur.ry, dir);
                int[] blue = move(cur.bx, cur.by, dir);

                int rnx = red[0], rny = red[1], rc = red[2];
                int bnx = blue[0], bny = blue[1], bc = blue[2];

                // 파란 구슬이 구멍에 빠진 경우
                if (graph[bnx][bny] == 'O') continue;

                // 빨간 구슬만 빠진 경우
                if (graph[rnx][rny] == 'O') {
                    System.out.println(cur.depth + 1);
                    return;
                }

                // 두 구슬이 같은 위치에 있을 경우 이동 거리가 더 긴 구슬을 뒤로 
                if (rnx == bnx && rny == bny) {
                    if (rc > bc) {
                        rnx -= dx[dir];
                        rny -= dy[dir];
                    } else {
                        bnx -= dx[dir];
                        bny -= dy[dir];
                    }
                }

                if (!visited[rnx][rny][bnx][bny]) {
                    visited[rnx][rny][bnx][bny] = true;
                    q.add(new State(rnx, rny, bnx, bny, cur.depth + 1));
                }
            }
        }

        System.out.println(-1);
    }

    static int[] move(int x, int y, int dir) {
        int cnt = 0;
        while (true) {
            if (graph[x + dx[dir]][y + dy[dir]] == '#' || graph[x][y] == 'O') break;
            x += dx[dir];
            y += dy[dir];
            cnt++;
        }
        return new int[]{x, y, cnt}; 
    }
}
