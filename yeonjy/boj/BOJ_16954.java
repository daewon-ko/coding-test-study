package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16954 {
    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] board = new char[8][8];
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};

    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        int result = bfs();
        System.out.println(result);
        br.close();
    }

    static int bfs() {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0, 7));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Position cur = queue.poll();
                int x = cur.x;
                int y = cur.y;
                if (board[y][x] == '#')
                    continue;
                if (y == 0 && x == 7)
                    return 1;
                for (int j = 0; j < 9; j++) {
                    int tempX = x + dx[j];
                    int tempY = y + dy[j];
                    if (tempX >= 0 && tempY >= 0 && tempX < 8 && tempY < 8) {
                        if (board[tempY][tempX] == '.')
                            queue.add(new Position(tempX, tempY));
                    }
                }
            }
            wallDrop();
        }
        return 0;
    }

    static void wallDrop() {
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                board[i + 1][j] = board[i][j];
            }
        }
        for (int i = 0; i < 8; i++) {
            board[0][i] = '.';
        }
    }
}
