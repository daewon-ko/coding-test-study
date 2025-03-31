package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17836 {
    private static final int[] dx = new int[]{-1, 1, 0, 0};
    private static final int[] dy = new int[]{0, 0, -1, 1};
    static int N;
    static int M;
    static int T;
    static int[][] map;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());  // 제한시간
        map = new int[N + 1][M + 1];
        int[] gram = new int[2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    gram = new int[]{i, j};
                }
            }
        }

        int withoutGramTime = bfs(new int[]{1, 1}, new int[]{N, M}, false);
        int findGramTime = bfs(new int[]{1, 1}, gram, false);
        int withGramTime = bfs(gram, new int[]{N, M}, true);
        int gramTime = findGramTime + withGramTime;

        if (withoutGramTime == -1) {
            if (findGramTime == -1 || withGramTime == -1 || gramTime > T) {
                System.out.println("Fail");
                return;
            }
            System.out.println(gramTime);
            return;
        }
        int result = Math.min(withoutGramTime, gramTime);
        if (result > T) {
            System.out.println("Fail");
            return;
        }
        System.out.println(result);
    }

    static int bfs(int[] start, int[] finish, boolean withGram) {
        isVisited = new boolean[N + 1][M + 1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start[0], start[1], 0});  // x, y, time

        int finishX = finish[0];
        int finishY = finish[1];

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int time = now[2];
            if (now[0] == finishX && now[1] == finishY) {
                return time;
            }
            if (time > T) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (withGram && isPossibleWithGram(nx, ny) || !withGram && isPossibleWithoutGram(nx, ny)) {
                    q.add(new int[]{nx, ny, now[2] + 1});
                    isVisited[nx][ny] = true;
                }
            }
        }
        return -1;
    }

    static boolean isPossibleWithGram(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= M && !isVisited[x][y];
    }

    static boolean isPossibleWithoutGram(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= M && !isVisited[x][y] && map[x][y] != 1;
    }
}
