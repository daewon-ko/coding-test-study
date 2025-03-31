import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 공주님을 구해라! / 골드5
https://www.acmicpc.net/problem/17836
 */
public class BOJ_17836 {

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int N, M, T;
    private static int[][] map;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs();
        System.out.println(result <= T ? result : "Fail");
    }

    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, false));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.x == N - 1 && current.y == M - 1) {
                return current.time;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = current.x + dr[dir];
                int ny = current.y + dc[dir];
                int hasGram = current.hasGram ? 1 : 0;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                if (visited[nx][ny][hasGram]) {
                    continue;
                }

                if (current.hasGram) {
                    visited[nx][ny][1] = true;
                    queue.offer(new Node(nx, ny, current.time + 1, true));
                } else {
                    if (map[nx][ny] == 1) {
                        continue;
                    }

                    if (map[nx][ny] == 2) {
                        visited[nx][ny][1] = true;
                        queue.offer(new Node(nx, ny, current.time + 1, true));
                    } else {
                        visited[nx][ny][0] = true;
                        queue.offer(new Node(nx, ny, current.time + 1, false));
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static class Node {
        int x, y, time;
        boolean hasGram;

        Node(int x, int y, int time, boolean hasGram) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.hasGram = hasGram;
        }
    }
}

