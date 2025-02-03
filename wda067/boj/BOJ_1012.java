import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 유기농 배추 / 실버2
https://www.acmicpc.net/problem/1012
 */
public class BOJ_1012 {

    private static final int[] dr = {0, 1, 0, -1};
    private static final int[] dc = {1, 0, -1, 0};
    private static boolean[][] visit;
    private static int[][] map;
    private static int M;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); //가로
            N = Integer.parseInt(st.nextToken()); //세로
            int K = Integer.parseInt(st.nextToken()); //배추 개수
            visit = new boolean[N][M];
            map = new int[N][M];
            int count = 0;

            for (int cabbage = 0; cabbage < K; cabbage++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                map[Y][X] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    if (map[i][j] == 1 && !visit[i][j]) {
                        dfs(i, j);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void dfs(int r, int c) {
        visit[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];

            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                continue;
            }

            if (map[nextR][nextC] == 1 && !visit[nextR][nextC]) {
                dfs(nextR, nextC);
            }

        }
    }
}
