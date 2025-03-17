import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 인구 이동 / 골드4
https://www.acmicpc.net/problem/16234
 */
public class BOJ_16234 {

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    private static int N, L, R;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        //인구 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        while (true) {
            visited = new boolean[N][N];
            boolean flag = false;  //연합 형성 여부

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        flag |= bfs(new int[]{i, j});  //한 번이라도 연합이 형성되면 true
                    }
                }
            }

            if (!flag) {
                break;
            }

            count++;
        }

        System.out.println(count);
    }

    private static boolean bfs(int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start[0], start[1]});
        visited[start[0]][start[1]] = true;
        List<int[]> party = new LinkedList<>();
        party.add(start);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int d = 0; d < 4; d++) {
                int nextR = curR + dr[d];
                int nextC = curC + dc[d];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) {
                    continue;
                }

                int abs = Math.abs(map[nextR][nextC] - map[curR][curC]);
                boolean flag = abs <= R && L <= abs;

                if (!visited[nextR][nextC] && flag) {
                    visited[nextR][nextC] = true;
                    q.add(new int[]{nextR, nextC});
                    party.add(new int[]{nextR, nextC});
                }
            }
        }

        if (party.size() == 1) {  //연합이 만들어지지 않은 경우
            return false;
        }

        int sum = party.stream()
                .map(i -> map[i[0]][i[1]])
                .mapToInt(Integer::intValue)
                .sum();

        for (int[] ints : party) {
            map[ints[0]][ints[1]] = sum / party.size();
        }

        return true;
    }
}
