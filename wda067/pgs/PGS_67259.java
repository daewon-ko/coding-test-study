import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
프로그래머스 / 경주로 건설 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/67259
 */
class PGS_67259 {

    private int[] dr = {-1, 0, 1, 0};
    private int[] dc = {0, 1, 0, -1};

    private int N;
    private int[][] board;
    private int[][][] cost;

    public int solution(int[][] board) {
        N = board.length;
        this.board = board;
        cost = new int[N][N][4];  //r, c, 방향

        for (int[][] row : cost) {
            for (int[] col : row) {
                Arrays.fill(col, Integer.MAX_VALUE);
            }
        }

        return bfs();
    }

    private int bfs() {
        Queue<int[]> q = new LinkedList<>();
        for (int d = 0; d < 4; d++) {
            cost[0][0][d] = 0;
            q.add(new int[]{0, 0, d, 0});  //r, c, 방향, 누적 비용
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int dir = cur[2];
            int curCost = cur[3];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                if (board[nr][nc] == 1) {
                    continue;
                }

                //방향이 전환된다면 600원
                int nextCost = curCost + (dir == d ? 100 : 600);
                if (cost[nr][nc][d] > nextCost) {  //더 저렴한 비용일 경우
                    cost[nr][nc][d] = nextCost;
                    q.add(new int[]{nr, nc, d, nextCost});
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, cost[N - 1][N - 1][d]);
        }
        return answer;
    }
}
