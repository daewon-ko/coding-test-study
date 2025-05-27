import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 감시 / 골드3
https://www.acmicpc.net/problem/15683
 */
public class BOJ_15683 {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    private static int[][] map;
    private static int N, M, min = Integer.MAX_VALUE;
    private static List<Integer> cctvs = new ArrayList<>();
    private static List<int[]> pos = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        //0: 빈칸, 6: 벽
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num != 0 && num != 6) {  //CCTV일 경우
                    cctvs.add(num);
                    pos.add(new int[]{i, j});
                }
                if (num == 6) {  //벽일 경우
                    visited[i][j] = true;
                }
            }
        }

        dfs(0, visited);

        System.out.println(min);
    }

    private static void dfs(int index, boolean[][] visited) {
        if (cctvs.size() == index) {
            int total = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j]) {
                        total++;
                    }
                }
            }

            min = Math.min(min, total);
            return;
        }

        int cctv = cctvs.get(index);
        for (int dir = 0; dir < 4; dir++) {
            boolean[][] clone = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                clone[i] = visited[i].clone();
            }

            check(cctv, dir, clone, index);
            dfs(index + 1, clone);
        }
    }

    private static void check(int cctv, int dir, boolean[][] visited, int index) {
        int r = pos.get(index)[0];
        int c = pos.get(index)[1];
        visited[r][c] = true;

        if (cctv == 1) {
            processChecking(dir, visited, r, c);
        } else if (cctv == 2) {
            processChecking(dir, visited, r, c);
            processChecking((dir + 2) % 4, visited, r, c);
        } else if (cctv == 3) {
            processChecking(dir, visited, r, c);
            processChecking((dir + 1) % 4, visited, r, c);
        } else if (cctv == 4) {
            processChecking(dir, visited, r, c);
            processChecking((dir + 1) % 4, visited, r, c);
            processChecking((dir + 2) % 4, visited, r, c);
        } else if (cctv == 5) {
            processChecking(dir, visited, r, c);
            processChecking((dir + 1) % 4, visited, r, c);
            processChecking((dir + 3) % 4, visited, r, c);
            processChecking((dir + 2) % 4, visited, r, c);
        }
    }

    private static void processChecking(int dir, boolean[][] visited, int nr, int nc) {
        while (true) {
            nr += dr[dir];
            nc += dc[dir];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                break;
            }

            if (map[nr][nc] == 6) {  //벽일 경우
                break;
            }

            visited[nr][nc] = true;
        }
    }

}
