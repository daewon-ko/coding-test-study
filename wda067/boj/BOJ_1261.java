import static java.util.Comparator.comparingInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 알고스팟 / 골드4
https://www.acmicpc.net/problem/1261
 */
public class BOJ_1261 {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static int[][] map;
    private static boolean[][] visited;
    private static int M, N, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());  //가로
        N = Integer.parseInt(st.nextToken());  //세로
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = charArray[j] - '0';
            }
        }

        bfs();  //우선 순위 큐를 이용한 다익스트라

        System.out.println(result);
    }

    private static void bfs() {
        //인접 노드 중에서 빈 방부터 탐색
        PriorityQueue<Node> pq = new PriorityQueue<>(comparingInt(o -> o.cost));
        pq.add(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.r == N - 1 && cur.c == M - 1) {  //도착 시 종료
                result = cur.cost;
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nextR = cur.r + DR[dir];
                int nextC = cur.c + DC[dir];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }

                if (!visited[nextR][nextC]) {
                    visited[nextR][nextC] = true;

                    if (map[nextR][nextC] == 1) {  //벽일 경우
                        pq.add(new Node(nextR, nextC, cur.cost + 1));
                    } else {
                        pq.add(new Node(nextR, nextC, cur.cost));
                    }
                }
            }
        }
    }

    private static class Node {
        int r, c, cost;

        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
}
