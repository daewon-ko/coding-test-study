package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14502 {
    static final int[] dx = new int[]{-1, 1, 0, 0};
    static final int[] dy = new int[]{0, 0, -1, 1};

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] isVisited;
    static int safe;
    static int answer = 0;
    static List<int[]> viruses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    viruses.add(new int[]{i, j});
                } else if (map[i][j] == 0) {
                    safe++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(i, j, 1);
                    map[i][j] = 0;
                }
            }
        }

        System.out.println(answer - 3);
    }

    static void dfs(int x, int y, int count) {
        if (count == 3) {
            isVisited = new boolean[N][M];
            int ans = bfs();
            if (ans > answer) {
                answer = ans;
            }
            return;
        }

        for (int i = y + 1; i < M; i++) {
            if (map[x][i] == 0) {
                map[x][i] = 1;
                dfs(x, i, count + 1);
                map[x][i] = 0;
            }
        }

        for (int i = x + 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(i, j, count + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>(viruses);
        int nowSafe = safe;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (isValid(nx, ny) && map[nx][ny] == 0 && !isVisited[nx][ny]) {
                    isVisited[nx][ny] = true;
                    nowSafe--;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return nowSafe;
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
