import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 감시 피하기 / 골드5
https://www.acmicpc.net/problem/18428
 */
public class BOJ_18428 {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    private static char[][] map;
    private static int N;
    private static boolean answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        //T: 선생님, S: 학생, O: 장애물
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'X') {
                    boolean[][] obstacle = new boolean[N][N];
                    obstacle[i][j] = true;
                    combination(i, j, 1, obstacle);
                }
            }
        }

        System.out.println(answer ? "YES" : "NO");
    }

    //장애물을 놓는 모든 경우의 수
    private static void combination(int r, int c, int depth, boolean[][] obstacle) {
        if (depth == 3) {
            if (move(obstacle)) {  //학생을 발견하지 못한 경우
                answer = true;
            }
            return;
        }

        int nr = r;
        int nc = c + 1;
        if (nc == N) {
            nc = 0;
            nr++;
        }

        if (nr == N) {  //범위를 벗어나면 종료
            return;
        }

        if (map[nr][nc] == 'X') {  //빈칸일 경우
            obstacle[nr][nc] = true;
            combination(nr, nc, depth + 1, obstacle);  //장애물을 놓고 재귀 호출
            obstacle[nr][nc] = false;
        }

        combination(nr, nc, depth, obstacle);  //장애물을 놓지 않고 재귀 호출
    }

    private static boolean move(boolean[][] obstacle) {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'T') {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nr = r;
                int nc = c;

                while (true) {
                    nr += dr[dir];
                    nc += dc[dir];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        break;
                    }

                    if (obstacle[nr][nc]) {  //장애물일 경우
                        break;
                    }

                    if (map[nr][nc] == 'S') {  //학생 탐색시 종료
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
