import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
백준 / Puyo Puyo / 골드4
https://www.acmicpc.net/problem/11559
 */
public class BOJ_11559 {

    static char[][] map = new char[12][6];
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean isBoom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int chain = 0;

        while (true) {
            isBoom = false;
            visited = new boolean[12][6];

            // 1. 터질 뿌요 탐색 및 폭발
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        bfs(i, j, map[i][j]);
                    }
                }
            }

            // 터질 뿌요가 없으면 종료
            if (!isBoom) {
                break;
            }

            // 2. 중력 작용
            gravity();

            chain++;
        }

        System.out.println(chain);
    }

    static void bfs(int x, int y, char color) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> puyos = new ArrayList<>();
        queue.add(new int[]{x, y});
        puyos.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) {
                    continue;
                }
                if (visited[nx][ny] || map[nx][ny] != color) {
                    continue;
                }

                queue.add(new int[]{nx, ny});
                puyos.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        if (puyos.size() >= 4) {
            isBoom = true;
            for (int[] p : puyos) {
                map[p[0]][p[1]] = '.';
            }
        }
    }

    static void gravity() {
        for (int j = 0; j < 6; j++) {
            Queue<Character> queue = new LinkedList<>();

            for (int i = 11; i >= 0; i--) {
                if (map[i][j] != '.') {
                    queue.add(map[i][j]);
                    map[i][j] = '.';
                }
            }

            int idx = 11;
            while (!queue.isEmpty()) {
                map[idx--][j] = queue.poll();
            }
        }
    }
}