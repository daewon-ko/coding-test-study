import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 2048 (Easy) / 골드1
https://www.acmicpc.net/problem/12100
 */
public class BOJ_12100 {

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    private static int N, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer sb = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(sb.nextToken());
            }
        }

        dfs(board, 0);
        System.out.println(max);
    }

    private static void dfs(int[][] board, int depth) {
        if (depth == 5) {
            for (int[] row : board) {
                for (int val : row) {
                    max = Math.max(max, val);
                }
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int[][] newBoard = move(board, d);
            dfs(newBoard, depth + 1);
        }
    }

    private static int[][] move(int[][] board, int dir) {
        int[][] newBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            newBoard[i] = board[i].clone();
        }

        boolean[][] merged = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int r = (dir == 0) ? i : (dir == 1) ? N - 1 - i : j;
                int c = (dir == 2) ? i : (dir == 3) ? N - 1 - i : j;

                if (newBoard[r][c] == 0) {
                    continue;
                }

                int nr = r;
                int nc = c;

                while (true) {
                    int tr = nr + dr[dir];
                    int tc = nc + dc[dir];

                    if (!inRange(tr, tc)) {
                        break;
                    }

                    if (newBoard[tr][tc] == 0) {
                        newBoard[tr][tc] = newBoard[nr][nc];
                        newBoard[nr][nc] = 0;
                        nr = tr;
                        nc = tc;
                    } else if (newBoard[tr][tc] == newBoard[nr][nc] && !merged[tr][tc]) {
                        newBoard[tr][tc] *= 2;
                        newBoard[nr][nc] = 0;
                        merged[tr][tc] = true;
                        break;
                    } else {
                        break;
                    }
                }
            }
        }

        return newBoard;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
