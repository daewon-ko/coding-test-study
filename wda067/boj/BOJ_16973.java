import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 직사각형 탈출 / 골드4
https://www.acmicpc.net/problem/16973
 */
public class BOJ_16973 {

    static int N, M, H, W, Sr, Sc, Fr, Fc;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Sr = Integer.parseInt(st.nextToken()) - 1;
        Sc = Integer.parseInt(st.nextToken()) - 1;
        Fr = Integer.parseInt(st.nextToken()) - 1;
        Fc = Integer.parseInt(st.nextToken()) - 1;

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{Sr, Sc, 0});
        visited[Sr][Sc] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];

            if (r == Fr && c == Fc) {
                return dist;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nextR = r + dr[dir];
                int nextC = c + dc[dir];

                if (nextR < 0 || nextR + H - 1 >= N || nextC < 0 || nextC + W - 1 >= M) {
                    continue;
                }

                if (!visited[nextR][nextC]) {
                    boolean flag = true;
                    for (int i = nextR; i < nextR + H; i++) {
                        for (int j = nextC; j < nextC + W; j++) {
                            if (map[i][j] == 1) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            break;
                        }
                    }

                    if (flag) {
                        visited[nextR][nextC] = true;
                        queue.add(new int[]{nextR, nextC, dist + 1});
                    }
                }
            }
        }

        return -1;
    }

}

