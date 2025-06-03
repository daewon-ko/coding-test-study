import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 알파벳 / 골드4
https://www.acmicpc.net/problem/1987
 */
public class BOJ_1987 {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    private static char[][] board;
    private static int R, C, max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        //List<Character> list = new ArrayList<>();
        //list.add(board[0][0]);
        //dfs(0, 0, list);

        int initBit = 1 << (board[0][0] - 'A');
        dfs(0, 0, initBit, 1);

        System.out.println(max);
    }

    private static void dfs(int r, int c, List<Character> list) {
        max = Math.max(max, list.size());

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                continue;
            }
            char cur = board[nr][nc];

            if (!list.contains(cur)) {  //O(n) -> 비효율적
                list.add(cur);
                dfs(nr, nc, list);
                list.remove(list.size() - 1);
            }
        }
    }

    private static void dfs(int r, int c, int used, int count) {
        max = Math.max(max, count);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                continue;
            }

            int nextBit = 1 << (board[nr][nc] - 'A');
            if ((used & nextBit) == 0) {  //0 & 1 = 0
                dfs(nr, nc, used | nextBit, count + 1);
            }
        }
    }
}
