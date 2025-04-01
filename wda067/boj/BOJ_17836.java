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
    private static int[][] castle;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        //성 초기화
        castle = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N + 1][M + 1][2];  //0 -> 그람 x, 1 -> 그람 o
        int result = bfs();

        if (result == -1) {
            System.out.println("Fail");
            return;
        }

        System.out.println(result);
    }

    private static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 1, 0, false));
        visited[1][1][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.time > T) {
                break;
            }
            if (cur.r == N && cur.c == M) {
                return cur.time;
            }

            for (int d = 0; d < 4; d++) {
                int nextR = cur.r + dr[d];
                int nextC = cur.c + dc[d];

                if (nextR < 1 || nextR > N || nextC < 1 || nextC > M) {
                    continue;
                }

                boolean[] nextVisited = visited[nextR][nextC];
                if (!cur.hasGram) {  //그람 미획득 상태
                    if (!nextVisited[0]) {
                        if (castle[nextR][nextC] == 0) {
                            nextVisited[0] = true;
                            q.add(new Node(nextR, nextC, cur.time + 1, false));
                        } else if (castle[nextR][nextC] == 2) {  //그람 획득
                            nextVisited[1] = true;
                            q.add(new Node(nextR, nextC, cur.time + 1, true));
                        }
                    }
                } else {  //그람 획득 상태
                    if (!nextVisited[1]) {
                        q.add(new Node(nextR, nextC, cur.time + 1, true));
                        nextVisited[1] = true;
                    }
                }
            }
        }

        return -1;
    }

    private static class Node {

        int r, c, time;
        boolean hasGram;

        public Node(int r, int c, int time, boolean hasGram) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.hasGram = hasGram;
        }
    }
}
