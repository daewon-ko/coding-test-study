package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_21610 {
    static class Cloud {
        int x;
        int y;
        Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void move(int d, int s) {
            for (int i = 0; i < s; i++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx > N) {
                    nx = 1;
                } else if (nx < 1) {
                    nx = N;
                }

                if (ny > N) {
                    ny = 1;
                } else if (ny < 1) {  // ny == 0 일 때
                    ny = N;
                }

                x = nx;
                y = ny;
            }
        }
    }

    static final int[] dx = new int[]{0, 0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] dy = new int[]{0, -1, -1, 0, 1, 1, 1, 0, -1};

    static int N;
    static int M;
    static int[][] map;
    static List<Cloud> clouds = new ArrayList<>();
    static boolean[][] cloudMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds.add(new Cloud(N, 1));
        clouds.add(new Cloud(N, 2));
        clouds.add(new Cloud(N - 1, 1));
        clouds.add(new Cloud(N - 1, 2));

        for (int i = 0; i < M; i++) {
            cloudMap = new boolean[N + 1][N + 1];
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            solution(d, s);
        }

        System.out.println(getTotalWater());
    }

    static void solution(int d, int s) {
        for (Cloud cloud : clouds) {
            // 1) 구름 이동
            cloud.move(d, s);
            // 2) 도착한 구름 위치의 물의 양 + 1
            map[cloud.x][cloud.y]++;
        }

        for (Cloud cloud : clouds) {
            int x = cloud.x;
            int y = cloud.y;

            // 4) 물복사버그 마법
            duplicateWater(x, y);

            cloudMap[x][y] = true;
        }

        // 3) 구름 사라짐
        clouds = new ArrayList<>();

        // 5) 구름 생성 & 물의 양 2 줄어듬
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] > 1 && !cloudMap[i][j]) {
                    // 5-1) 구름 생성
                    clouds.add(new Cloud(i, j));
                    // 5-2) 물의 양 2 줄어듬
                    map[i][j] -= 2;
                }
            }
        }
    }

    static void duplicateWater(int x, int y) {
        int cnt = 0;
        for (int i = 2; i <= 8; i += 2) {  // 대각선
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx > 0 && ny > 0 && nx <= N && ny <= N && map[nx][ny] > 0) {
                cnt++;
            }
        }
        map[x][y] += cnt;
    }

    static int getTotalWater() {
        int total = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                total += map[i][j];
            }
        }
        return total;
    }
}
