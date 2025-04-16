import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
백준 / 움직이는 미로 탈출 / 골드3
https://www.acmicpc.net/problem/16954
 */
public class BOJ_16954 {

    private static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0, 0};
    private static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1, 0};

    private static char[][] board = new char[8][8];
    static boolean[][][] visited = new boolean[8][8][9]; //row, col, time

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("wda067/io/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 8; i++) {
            String s = br.readLine();
            board[i] = s.toCharArray();
        }

        System.out.println(bfs() ? 1 : 0);
    }

    private static boolean bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{7, 0, 0});  //좌측 하단
        visited[7][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int time = cur[2];

            if (curR == 0 && curC == 7) {  //목적지 도달
                return true;
            }

            for (int d = 0; d < 9; d++) {
                int nextR = curR + dr[d];
                int nextC = curC + dc[d];
                int nextTime = Math.min(8, time + 1);

                if (nextR < 0 || nextR >= 8 || nextC < 0 || nextC >= 8) {
                    continue;
                }

                if (!visited[nextR][nextC][nextTime]) {
                    //현재 위치의 현재 시간과 이동 후에 벽이 없을 때
                    if (getBoard(nextR, nextC, time) == '.' && getBoard(nextR, nextC, nextTime) == '.') {
                        visited[nextR][nextC][nextTime] = true;
                        q.add(new int[]{nextR, nextC, nextTime});
                    }
                }
            }
        }

        return false;
    }

    //t초일 때 (r, c)에 벽이 있는지 확인
    private static char getBoard(int r, int c, int t) {
        int nextR = r - t;  //t초 전의 행
        if (nextR < 0) {  //벽이 사라짐
            return '.';
        }
        return board[nextR][c];
    }
}
