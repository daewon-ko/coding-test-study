import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
백준 / 빙산 / 골드4
https://www.acmicpc.net/problem/2573
 */
public class BOJ_2573 {

    private static final int MAX = 300;

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};
    private static int[][] map;
    private static int N, M;
    private static int[][][] check;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        check = new int[MAX + 1][N][M];  //n년이 자났을 때 (r, c)의 높이

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                check[0][i][j] = map[i][j];
            }
        }

        bfs();

        for (int t = 0; t <= MAX; t++) {
            parent = new int[N * M];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (check[t][r][c] == 0) {
                        parent[r * M + c] = -1;
                    } else {
                        parent[r * M + c] = r * M + c;
                    }
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (check[t][r][c] == 0) {
                        continue;
                    }

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                            continue;
                        }
                        if (check[t][nr][nc] == 0) {
                            continue;
                        }

                        int a = r * M + c;
                        int b = nr * M + nc;
                        union(a, b);
                    }
                }
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < N * M; i++) {
                if (parent[i] != -1) {  //바다가 아닐 때
                    set.add(find(i));
                }
            }

            if (set.size() > 1) {
                System.out.println(t);
                return;
            }
        }

        System.out.println(0);
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    q.add(new int[]{i, j, 0});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int t = cur[2];

            if (t == MAX) {
                continue;
            }

            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                if (check[t][nr][nc] == 0) {  //t시간일 때 높이가 0이면 카운트
                    count++;
                }
            }

            check[t + 1][r][c] = Math.max(0, check[t][r][c] - count);
            if (check[t + 1][r][c] > 0) {
                q.add(new int[]{r, c, t + 1});
            }
        }
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }
}



