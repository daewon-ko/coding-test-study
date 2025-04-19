import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
프로그래머스 / 경주로 건설 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/67259
 */
class PGS_67259 {

    public int solution(int[][] board) {
        int n = board.length;
        int[][][] cost = new int[n][n][4];
        for (int[][] layer : cost)
            for (int[] row : layer)
                Arrays.fill(row, Integer.MAX_VALUE);

        int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
        int[] dy = {0, 0, -1, 1};

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            cost[0][0][i] = 0;
            queue.offer(new Node(0, 0, 0, i));
        }

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0) {
                    int newCost = curr.cost + ((curr.dir == i) ? 100 : 600);
                    if (cost[nx][ny][i] > newCost) {
                        cost[nx][ny][i] = newCost;
                        queue.offer(new Node(nx, ny, newCost, i));
                    }
                }
            }
        }

        return Arrays.stream(cost[n - 1][n - 1]).min().getAsInt();
    }

    private static class Node {
        int x, y, cost, dir;

        Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }
}


