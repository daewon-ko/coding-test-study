import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 마법사 상어와 비바라기 / 골드5
https://www.acmicpc.net/problem/21610
 */
public class BOJ_21610 {

    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    private static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> clouds = new LinkedList<>();
        clouds.add(new int[]{N, 1});
        clouds.add(new int[]{N, 2});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 1, 2});

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            visited = new boolean[N + 1][N + 1];
            Queue<int[]> newClouds = new LinkedList<>();

            while (!clouds.isEmpty()) {
                int[] cur = clouds.poll();
                int nr = (cur[0] + dr[d] * s) % N;
                int nc = (cur[1] + dc[d] * s) % N;

                if (nr <= 0) nr += N;
                if (nc <= 0) nc += N;

                map[nr][nc]++;
                visited[nr][nc] = true;
                newClouds.add(new int[]{nr, nc});
            }

            for (int[] cur : newClouds) {
                int r = cur[0];
                int c = cur[1];
                int count = 0;

                for (int i = 1; i < 8; i += 2) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] > 0) {
                        count++;
                    }
                }
                map[r][c] += count;
            }

            clouds = new LinkedList<>();
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (map[r][c] >= 2 && !visited[r][c]) {
                        clouds.add(new int[]{r, c});
                        map[r][c] -= 2;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                result += map[i][j];
            }
        }
        System.out.println(result);
    }
}
