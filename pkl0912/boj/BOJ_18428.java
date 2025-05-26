package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_18428 {
    static int n;
    static String[][] board;
    static boolean answer = false;
    static List<int[]> teacherList = new ArrayList<>();
    static List<int[]> emptyList = new ArrayList<>();
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new String[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = st.nextToken();
                if (board[i][j].equals("T")) teacherList.add(new int[]{i, j});
                else if (board[i][j].equals("X")) emptyList.add(new int[]{i, j});
            }
        }

        dfs(0, 0, new ArrayList<>(), new boolean[n][n]);

        System.out.println(answer ? "YES" : "NO");
    }

    static void dfs(int start, int count, List<int[]> selected, boolean[][] visited) {
        if (answer) return;

        if (count == 3) {
            String[][] newBoard = new String[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(board[i], 0, newBoard[i], 0, n);
            }
            for (int[] sel : selected) {
                newBoard[sel[0]][sel[1]] = "O";
            }

            if (check(newBoard)) answer = true;
            return;
        }

        for (int i = start; i < emptyList.size(); i++) {
            int x = emptyList.get(i)[0];
            int y = emptyList.get(i)[1];
            if (!visited[x][y]) {
                selected.add(new int[]{x, y});
                visited[x][y] = true;
                dfs(i + 1, count + 1, selected, visited);
                visited[x][y] = false;
                selected.remove(selected.size() - 1);
            }
        }
    }

    static boolean check(String[][] map) {
        for (int[] teacher : teacherList) {
            int x = teacher[0];
            int y = teacher[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = x;
                int ny = y;
                while (true) {
                    nx += dx[dir];
                    ny += dy[dir];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) break;
                    if (map[nx][ny].equals("O")) break; 
                    if (map[nx][ny].equals("S")) return false; 
                }
            }
        }
        return true;
    }
}
