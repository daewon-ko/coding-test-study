package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16973 {
    static final int[] dx = new int[]{-1, 1, 0, 0};
    static final int[] dy = new int[]{0, 0, -1, 1};

    static int N;
    static int M;
    static int H;
    static int W;
    static int[][] map;
    static int[][] rowWalls;
    static int[][] colWalls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        rowWalls = new int[N + 1][M + 1];
        colWalls = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                rowWalls[i][j] = rowWalls[i][j - 1] + map[i][j];
            }
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                colWalls[j][i] = colWalls[j - 1][i] + map[j][i];
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int startRow = Integer.parseInt(st.nextToken());
        int startCol = Integer.parseInt(st.nextToken());
        int finishRow = Integer.parseInt(st.nextToken());
        int finishCol = Integer.parseInt(st.nextToken());

        int res = bfs(startRow, startCol, finishRow, finishCol);
        System.out.println(res);
    }

    static int bfs(int startX, int startY, int finX, int finY) {
        boolean[][] isVisited = new boolean[N + 1][M + 1];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int depth = now[2];

            if (x == finX && y == finY) {
                return depth;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 1 && ny >= 1 && nx + H - 1 <= N && ny + W - 1 <= M && !isVisited[nx][ny]) {
                    if (i == 0) {  // 상
                        int cnt = rowWalls[nx][ny + W - 1] - rowWalls[nx][ny - 1];
                        if (cnt == 0) {
                            q.add(new int[]{nx, ny, depth + 1});
                        }
                    } else if (i == 1) {  // 하
                        int cnt = rowWalls[nx + H - 1][ny + W - 1] - rowWalls[nx + H - 1][ny - 1];
                        if (cnt == 0) {
                            q.add(new int[]{nx, ny, depth + 1});
                        }
                    } else if (i == 2) {  // 좌
                        int cnt = colWalls[nx + H - 1][ny] - colWalls[nx - 1][ny];
                        if (cnt == 0) {
                            q.add(new int[]{nx, ny, depth + 1});
                        }
                    } else {  // 우
                        int cnt = colWalls[nx + H - 1][ny + W - 1] - colWalls[nx - 1][ny + W - 1];
                        if (cnt == 0) {
                            q.add(new int[]{nx, ny, depth + 1});
                        }
                    }
                    isVisited[nx][ny] = true;
                }
            }

        }
        return -1;
    }
}
