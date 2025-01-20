package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499 {
    static final int[] dx = new int[]{0, 0, -1, 1};  // 동 서 북 남
    static final int[] dy = new int[]{1, -1, 0, 0};

    static int[] dice = new int[7];
    static int N;
    static int M;
    static int x;
    static int y;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int d = Integer.parseInt(st.nextToken());
            move(d);
        }
    }

    static void move(int d) {
        int nx = x + dx[d - 1];
        int ny = y + dy[d - 1];
        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
            return;
        }
        roll(d, nx, ny);
        x = nx;
        y = ny;
    }

    static void roll(int d, int x, int y) {
        int tmp = dice[3];
        if (d == 1) {
            dice[3] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[2];
            dice[2] = tmp;
        } else if (d == 2) {
            dice[3] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[4];
            dice[4] = tmp;
        } else if (d == 3) {
            dice[3] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[1];
            dice[1] = tmp;
        } else {
            dice[3] = dice[1];
            dice[1] = dice[6];
            dice[6] = dice[5];
            dice[5] = tmp;
        }

        if (map[x][y] == 0) {
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }
        System.out.println(dice[3]);
    }
}
