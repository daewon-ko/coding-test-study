import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 탈출 / 골드4
https://www.acmicpc.net/problem/3055
 */
public class BOJ_3055 {

    static int R, C;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] water;
    static int[][] dochi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        water = new int[R][C];
        dochi = new int[R][C];

        /*
        . -> 빈 곳
        * -> 물
        X -> 돌
        D -> 굴
        S -> 고슴도치 위치
         */
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int[] start = new int[2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'S') {
                    start = new int[]{i, j};
                    dochi[i][j] = 1;
                } else if (map[i][j] == '*') {
                    water[i][j] = 1;
                }
            }
        }

        waterBfs();
        dochiBfs(start);
    }

    static void waterBfs() {
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < R; i++) {  //물이 있는 위치 모두 추가
            for (int j = 0; j < C; j++) {
                if (water[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nextR = curR + dr[dir];
                int nextC = curC + dc[dir];

                if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) {
                    continue;
                }

                //빈 곳에만 물이 확산
                if (map[nextR][nextC] == '.' && water[nextR][nextC] == 0) {
                    water[nextR][nextC] = water[curR][curC] + 1;
                    q.add(new int[]{nextR, nextC});
                }
            }
        }
    }

    static void dochiBfs(int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nextR = curR + dr[dir];
                int nextC = curC + dc[dir];

                if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) {
                    continue;
                }

                if (map[nextR][nextC] == 'D') {  //굴에 도착
                    System.out.println(dochi[curR][curC]);
                    return;
                }

                if (map[nextR][nextC] == '.' && dochi[nextR][nextC] == 0) {
                    //물이 없거나 물이 확산될 때보다 먼저 도달할 수 있을 경우
                    if (water[nextR][nextC] == 0 || dochi[curR][curC] + 1 < water[nextR][nextC]) {
                        dochi[nextR][nextC] = dochi[curR][curC] + 1;
                        q.add(new int[]{nextR, nextC});
                    }
                }
            }
        }

        System.out.println("KAKTUS");
    }
}