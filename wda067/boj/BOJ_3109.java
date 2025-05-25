import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 빵집 / 골드2
https://www.acmicpc.net/problem/3109
 */
public class BOJ_3109 {

    private static int[] dr = {-1, 0, 1};
    private static int[] dc = {1, 1, 1};

    private static char[][] map;
    private static boolean[][] visited;
    private static int R, C, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            dfs(i, 0);
        }

        System.out.println(count);
    }

    private static boolean dfs(int r, int c) {
        if (c == C - 1) {
            count++;
            return true;
        }

        for (int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                continue;
            }

            if (map[nr][nc] == 'x') {
                continue;
            }

            if (!visited[nr][nc]) {
                visited[nr][nc] = true;

                if (dfs(nr, nc)) {
                    return true;
                }
            }
        }

        return false;
    }
}
