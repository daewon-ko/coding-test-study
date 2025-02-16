import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
백준 / 벽 부수고 이동하기 4 / 골드2
https://www.acmicpc.net/problem/16946
 */
public class BOJ_16946 {

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int N, M, groupNumber;
    private static int[][] map, group;
    private static boolean[][] visited;
    private static int[] groupSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        group = new int[N][M];
        visited = new boolean[N][M];
        groupSize = new int[N * M + 1];

        //맵 초기화
        for (int i = 0; i < N; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = charArray[j] - '0';
            }
        }

        //0인 좌표 그룹화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    groupNumber++;
                    bfs(i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {  //벽일 때만 계산
                    sb.append(getMoveCount(i, j) % 10);
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        group[r][c] = groupNumber;
        int size = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nextR = curR + dr[dir];
                int nextC = curC + dc[dir];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }

                if (!visited[nextR][nextC] && map[nextR][nextC] == 0) {
                    q.add(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                    group[nextR][nextC] = groupNumber;
                    size++;
                }
            }
        }

        groupSize[groupNumber] = size;
    }

    private static int getMoveCount(int r, int c) {
        Set<Integer> set = new HashSet<>();  //인접 그룹의 중복 제거
        int moveCount = 1;

        for (int dir = 0; dir < 4; dir++) {
            int nextR = r + dr[dir];
            int nextC = c + dc[dir];

            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                continue;
            }

            if (map[nextR][nextC] == 0) {
                int num = group[nextR][nextC];
                if (!set.contains(num)) {
                    moveCount += groupSize[num];  //인접 좌표의 그룹 크기 누적
                    set.add(num);
                }
            }
        }

        return moveCount;
    }
}
