package daewonko.boj;

import java.io.*;
import java.util.*;

public class BOJ_14442 {


    static class Node {
        int x, y, dist, breaks;

        Node(int x, int y, int dist, int breaks) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.breaks = breaks;
        }
    }

    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][K + 1];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 1, 0)); // 시작점 (0,0)에서 거리 1, 벽 부순 횟수 0
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.x == N - 1 && cur.y == M - 1) {
                return cur.dist; // 목표 도달 시 최단 거리 반환
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    // 벽이 아닐 경우 그냥 이동
                    if (map[nx][ny] == 0 && !visited[nx][ny][cur.breaks]) {
                        visited[nx][ny][cur.breaks] = true;
                        queue.offer(new Node(nx, ny, cur.dist + 1, cur.breaks));
                    }

                    // 벽이지만 부술 수 있는 횟수가 남아 있다면 부순다
                    if (map[nx][ny] == 1 && cur.breaks < K && !visited[nx][ny][cur.breaks + 1]) {
                        visited[nx][ny][cur.breaks + 1] = true;
                        queue.offer(new Node(nx, ny, cur.dist + 1, cur.breaks + 1));
                    }
                }
            }
        }

        return -1; // 도달 불가능한 경우
    }
}
