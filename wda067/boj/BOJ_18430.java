import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 무기 공학 / 골드4
https://www.acmicpc.net/problem/18430
 */
public class BOJ_18430 {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    private static int N, M, max = 0;
    private static int[][] hardness;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        hardness = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                hardness[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(max);
    }

    private static void dfs(int r, int c, int sum) {
        if (r == N) {  //모든 좌표 탐색 완료
            max = Math.max(max, sum);
            return;
        }

        //다음 좌표
        int nextR = r;
        int nextC = c + 1;
        if (nextC == M) {
            nextR++;
            nextC = 0;
        }

        //부메랑을 놓을 수 있는 경우 -> 놓고 진행, 안 놓고 진행
        //부메랑을 놓을 수 없는 경우 -> 안 놓고 진행

        if (!visited[r][c]) {  //현재 좌표를 사용하지 않은 경우
            for (int d = 0; d < 4; d++) {  //가능한 부메랑 좌표
                int nr = r + dr[d];
                int nc = c + dc[d];
                int nr2 = r + dr[(d + 1) % 4];
                int nc2 = c + dc[(d + 1) % 4];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
                    continue;
                }
                if (nr2 < 0 || nr2 >= N || nc2 < 0 || nc2 >= M || visited[nr2][nc2]) {
                    continue;
                }

                visited[r][c] = true;
                visited[nr][nc] = true;
                visited[nr2][nc2] = true;

                int weaponPower = hardness[r][c] * 2 + hardness[nr][nc] + hardness[nr2][nc2];
                dfs(nextR, nextC, sum + weaponPower);  //합을 누적하여 다음 좌표로 재귀 호출

                visited[r][c] = false;
                visited[nr][nc] = false;
                visited[nr2][nc2] = false;
            }
        }

        dfs(nextR, nextC, sum);  //현재 좌표를 사용하지 않고 재귀 호출
    }
}
