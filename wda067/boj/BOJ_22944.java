import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 죽음의 비 / 골드3
https://www.acmicpc.net/problem/22944
 */
public class BOJ_22944 {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};
    private static char[][] board;
    private static int N, H, D;
    private static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());  //체력
        D = Integer.parseInt(st.nextToken());  //내구도
        board = new char[N][N];
        visited = new int[N][N];

        int[] start = new int[2];
        int[] end = new int[2];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                board[i][j] = c;
                if (c == 'S') {
                    start = new int[]{i, j};
                } else if (c == 'E') {
                    end = new int[]{i, j};
                }
            }
        }

        System.out.println(bfs(start, end));
    }

    private static int bfs(int[] start, int[] end) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start[0], start[1], H, 0, 0));
        visited[start[0]][start[1]] = H;

        int min = Integer.MAX_VALUE;  //최소 이동거리

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;

            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                int h = cur.h;
                int cost = cur.cost;
                int cnt = cur.cnt;

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                if (nr == end[0] && nc == end[1]) {  //안전지대 도착시 min 갱신
                    min = Math.min(min, cnt + 1);
                    continue;
                }

                if (board[nr][nc] == 'U') {  //우산이면 내구도 설정
                    cost = D;
                }

                if (cost != 0) {  //우산 내구도가 있을 때
                    cost--;
                } else {
                    h--;
                }

                if (h == 0) {  //죽는 경우 다시 탐색
                    continue;
                }

                if (visited[nr][nc] < h + cost) {
                    visited[nr][nc] = h + cost;
                    q.add(new Node(nr, nc, h, cost, cnt + 1));
                }
            }
        }

        if (min == Integer.MAX_VALUE) {  //안전지대에 도달 X
            return -1;
        }
        return min;
    }

    private static class Node {
        int r, c, h, cost, cnt;

        public Node(int r, int c, int h, int cost, int cnt) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.cost = cost;
            this.cnt = cnt;
        }
    }
}