import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 캐슬 디펜스 / 골드4
https://www.acmicpc.net/problem/17135
 */
public class BOJ_17135 {

    private static int N, M, D, max;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //궁수 3명을 뽑는 모든 경우의 수 탐색
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {
                    List<Integer> archers = new ArrayList<>();
                    archers.add(i);
                    archers.add(j);
                    archers.add(k);
                    simulate(archers);
                }
            }
        }

        System.out.println(max);
    }

    private static void simulate(List<Integer> archers) {
        int[][] clone = new int[N][M];
        for (int i = 0; i < N; i++) {
            clone[i] = board[i].clone();
        }

        int count = 0;  //적 제거 카운트

        for (int i = 0; i < N; i++) {  //모든 적이 제외될 때까지 반복
            List<int[]> targets = new ArrayList<>();

            for (Integer archer : archers) {
                int[] target = bfs(clone, archer);  //현재 궁수에서 가장 가까운 적
                if (target != null) {
                    targets.add(target);
                }
            }

            for (int[] target : targets) {  //적 제거 및 카운트
                if (clone[target[0]][target[1]] == 1) {
                    clone[target[0]][target[1]] = 0;
                    count++;
                }
            }

            for (int j = N - 1; j > 0; j--) {  //적 아래로 한 칸 이동
                clone[j] = clone[j - 1].clone();
            }

            Arrays.fill(clone[0], 0);
        }

        max = Math.max(max, count);
    }

    private static int[] bfs(int[][] board, Integer archer) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new int[]{N - 1, archer, 1});  //행, 열, 거리
        int[] dr = {0, -1, 0};
        int[] dc = {-1, 0, 1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];

            if (dist > D) {  //거리 제한
                break;
            }

            if (board[r][c] == 1) {  //적일 경우
                return new int[]{r, c};
            }

            for (int dir = 0; dir < 3; dir++) {  //좌 -> 상 -> 우
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, dist + 1});
                }
            }
        }

        return null;
    }
}
