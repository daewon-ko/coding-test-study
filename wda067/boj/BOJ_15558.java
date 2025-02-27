import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15558 {

    static int n, k;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[2][n];

        for (int i = 0; i < 2; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs() ? 1 : 0);
    }

    private static boolean bfs() {
        boolean[][] visit = new boolean[2][n];
        int[] dc = {-1, 1, k};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        visit[0][0] = true;

        while (!q.isEmpty()) {
            int cur[] = q.poll();
            for (int i = 0; i < 3; i++) {
                int nc = cur[1] + dc[i];
                int nr = cur[0];
                int time = cur[2];

                if (i == 2) {
                    nr = (cur[0] == 1) ? 0 : 1;
                }

                if (nc >= n) {
                    return true;
                }
                if (nc <= time) {
                    continue;
                }
                if (visit[nr][nc]) {
                    continue;
                }
                if (map[nr][nc] == 0) {
                    continue;
                }

                visit[nr][nc] = true;
                q.add(new int[]{nr, nc, time + 1});
            }
        }
        return false;
    }
}
