import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
프로그래머스 / 카드 짝 맞추기 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/72415
 */
class PGS_72415 {

    private static int answer = Integer.MAX_VALUE;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static Map<Integer, List<Point>> cardPos = new HashMap<>();

    public int solution(int[][] board, int r, int c) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = board[i][j];
                if (num != 0) {
                    cardPos.computeIfAbsent(num, k -> new ArrayList<>()).add(new Point(i, j, 0));
                }
            }
        }

        List<Integer> cardNums = new ArrayList<>(cardPos.keySet());
        permute(cardNums, 0, board, r, c, 0);
        return answer;
    }

    private void permute(List<Integer> cards, int depth, int[][] board, int r, int c, int cnt) {
        if (depth == cards.size()) {
            answer = Math.min(answer, cnt);
            return;
        }

        for (int i = depth; i < cards.size(); i++) {
            Collections.swap(cards, i, depth);
            int card = cards.get(depth);
            List<Point> positions = cardPos.get(card);

            for (int j = 0; j < 2; j++) {
                Point first = positions.get(j);
                Point second = positions.get(1 - j);

                int toFirst = bfs(board, r, c, first.r, first.c);
                int toSecond = bfs(board, first.r, first.c, second.r, second.c);

                board[first.r][first.c] = 0;
                board[second.r][second.c] = 0;

                permute(cards, depth + 1, board, second.r, second.c, cnt + toFirst + toSecond + 2);

                board[first.r][first.c] = card;
                board[second.r][second.c] = card;
            }
            Collections.swap(cards, i, depth);
        }
    }

    private int bfs(int[][] board, int sr, int sc, int tr, int tc) {
        boolean[][] visited = new boolean[4][4];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(sr, sc, 0));
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.r == tr && p.c == tc) {
                return p.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (inRange(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new Point(nr, nc, p.cnt + 1));
                }

                int cr = p.r;
                int cc = p.c;
                while (true) {
                    int nrCtrl = cr + dr[d];
                    int ncCtrl = cc + dc[d];
                    if (!inRange(nrCtrl, ncCtrl)) {
                        break;
                    }
                    cr = nrCtrl;
                    cc = ncCtrl;
                    if (board[cr][cc] != 0) {
                        break;
                    }
                }
                if (!visited[cr][cc]) {
                    visited[cr][cc] = true;
                    queue.add(new Point(cr, cc, p.cnt + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private boolean inRange(int r, int c) {
        return r >= 0 && r < 4 && c >= 0 && c < 4;
    }

    private static class Point {
        int r, c, cnt;

        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}


