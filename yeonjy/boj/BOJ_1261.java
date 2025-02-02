package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1261 {
    static final int[] dx = new int[]{-1, 1, 0, 0};
    static final int[] dy = new int[]{0, 0, -1, 1};

    static int N;
    static int M;
    static int[][] map;
    static int[][] depths;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        depths = new int[N][M];
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        bfs();
        System.out.println(depths[N - 1][M - 1]);
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int depth = depths[now[0]][now[1]];
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (isValid(nx, ny)) {
                    if (map[nx][ny] == 0) {  // 빈칸일 때
                        if (depth < depths[nx][ny] || !isVisited[nx][ny]) {  // 갱신하려는 depth가 더 적을 때는 갱신
                            depths[nx][ny] = depth;
                            queue.add(new int[]{nx, ny});
                            isVisited[nx][ny] = true;
                        }
                    } else {  // 벽이 있는 칸일 때
                        if (depth + 1 < depths[nx][ny] || !isVisited[nx][ny]) {
                            depths[nx][ny] = depth + 1;
                            queue.add(new int[]{nx, ny});
                            isVisited[nx][ny] = true;
                        }
                    }
                }
            }
        }
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
