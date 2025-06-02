package pkl0912.pgs;

import java.util.*;

public class PGS_60063 {
    static class Pos {
        int x1, y1, x2, y2, cost;
        public Pos(int x1, int y1, int x2, int y2, int cost) {
            this.x1 = x1; this.y1 = y1;
            this.x2 = x2; this.y2 = y2;
            this.cost = cost;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        Queue<Pos> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        Pos start = new Pos(0, 0, 0, 1, 0);
        queue.offer(start);
        visited.add("0,0,0,1");

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            if ((cur.x1 == n-1 && cur.y1 == n-1) || (cur.x2 == n-1 && cur.y2 == n-1)) {
                return cur.cost;
            }

            // 1. 이동
            for (int[] d : dirs) {
                int nx1 = cur.x1 + d[0], ny1 = cur.y1 + d[1];
                int nx2 = cur.x2 + d[0], ny2 = cur.y2 + d[1];
                if (canMove(nx1, ny1, nx2, ny2, board)) {
                    String key = posKey(nx1, ny1, nx2, ny2);
                    if (!visited.contains(key)) {
                        visited.add(key);
                        queue.offer(new Pos(nx1, ny1, nx2, ny2, cur.cost + 1));
                    }
                }
            }

            // 2. 회전
            if (cur.x1 == cur.x2) { // 가로
                for (int dir : new int[]{-1, 1}) {
                    if (canMove(cur.x1 + dir, cur.y1, cur.x2 + dir, cur.y2, board)) {
                        addRotation(queue, visited, cur.x1, cur.y1, cur.x2, cur.y2, cur.cost, dir, true);
                    }
                }
            } else { // 세로
                for (int dir : new int[]{-1, 1}) {
                    if (canMove(cur.x1, cur.y1 + dir, cur.x2, cur.y2 + dir, board)) {
                        addRotation(queue, visited, cur.x1, cur.y1, cur.x2, cur.y2, cur.cost, dir, false);
                    }
                }
            }
        }
        return 0;
    }

    private boolean canMove(int x1, int y1, int x2, int y2, int[][] board) {
        int n = board.length;
        return isValid(x1, y1, n) && isValid(x2, y2, n)
                && board[x1][y1] == 0 && board[x2][y2] == 0;
    }

    private boolean isValid(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private void addRotation(Queue<Pos> q, Set<String> visited,
                             int x1, int y1, int x2, int y2, int cost, int dir, boolean isHorizontal) {
        if (isHorizontal) {
            String key1 = posKey(x1, y1, x1 + dir, y1);
            String key2 = posKey(x2, y2, x2 + dir, y2);
            if (visited.add(key1))
                q.offer(new Pos(x1, y1, x1 + dir, y1, cost + 1));
            if (visited.add(key2))
                q.offer(new Pos(x2, y2, x2 + dir, y2, cost + 1));
        } else {
            String key1 = posKey(x1, y1, x1, y1 + dir);
            String key2 = posKey(x2, y2, x2, y2 + dir);
            if (visited.add(key1))
                q.offer(new Pos(x1, y1, x1, y1 + dir, cost + 1));
            if (visited.add(key2))
                q.offer(new Pos(x2, y2, x2, y2 + dir, cost + 1));
        }
    }

    private String posKey(int x1, int y1, int x2, int y2) {
        // 두 좌표를 정렬해서 동일한 상태 중복 방지
        if (x1 < x2 || (x1 == x2 && y1 < y2))
            return x1 + "," + y1 + "," + x2 + "," + y2;
        else
            return x2 + "," + y2 + "," + x1 + "," + y1;
    }
}