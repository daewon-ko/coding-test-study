import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 미세먼지 안녕! / 골드4
https://www.acmicpc.net/problem/17144
 */
public class BOJ_17144 {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static int[] airPurifier = new int[2];
    private static int R, C;
    private static int[][] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        boolean flag = true;
        house = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());

                if (house[i][j] == -1 && flag) {
                    airPurifier[0] = i;
                    airPurifier[1] = i + 1;
                    flag = false;
                }
            }
        }

        //T초 동안 반복
        for (int i = 0; i < T; i++) {
            bfs();
            operateAirPurifier();
        }

        int sum = Arrays.stream(house)
                .flatMapToInt(Arrays::stream)
                .sum();

        System.out.println(sum + 2);  //공기 청정기 좌표값 제외
    }

    private static void operateAirPurifier() {
        int[][] clone = new int[R][C];
        for (int i = 0; i < R; i++) {
            clone[i] = house[i].clone();
        }

        int upperR = airPurifier[0];
        //상단
        for (int i = 2; i < C; i++) {
            house[upperR][i] = clone[upperR][i - 1];
        }

        for (int i = upperR - 1; i >= 0; i--) {
            house[i][C - 1] = clone[i + 1][C - 1];
        }

        for (int i = C - 2; i >= 0; i--) {
            house[0][i] = clone[0][i + 1];
        }

        for (int i = 1; i < upperR; i++) {
            house[i][0] = clone[i - 1][0];
        }

        //하단
        int lowerR = airPurifier[1];
        for (int i = 2; i < C; i++) {
            house[lowerR][i] = clone[lowerR][i - 1];
        }

        for (int i = lowerR + 1; i < R; i++) {
            house[i][C - 1] = clone[i - 1][C - 1];
        }

        for (int i = C - 2; i >= 0; i--) {
            house[R - 1][i] = clone[R - 1][i + 1];
        }

        for (int i = R - 2; i >= lowerR; i--) {
            house[i][0] = clone[i + 1][0];
        }

        //공기 청정기 좌표 재설정
        house[upperR][0] = -1;
        house[lowerR][0] = -1;

        house[upperR][1] = 0;
        house[lowerR][1] = 0;
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();

        //모든 먼지 좌표 큐에 삽입
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (house[i][j] != 0 && house[i][j] != -1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int[][] clone = new int[R][C];
        for (int i = 0; i < R; i++) {
            clone[i] = house[i].clone();
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextR = curR + DR[i];
                int nextC = curC + DC[i];

                if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) {
                    continue;
                }

                if (house[nextR][nextC] == -1) {
                    continue;
                }

                house[nextR][nextC] += (clone[curR][curC] / 5);
                house[curR][curC] -= (clone[curR][curC] / 5);
            }
        }
    }

}
