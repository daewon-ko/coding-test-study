package java.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15558 {
    static int N;
    static int k;
    static int[][] map;
    static boolean isSuccess;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[2][N];
        isVisited = new boolean[2][N];
        for (int i = 0; i < 2; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = line[j] - '0';
            }
        }

        int[] dc = new int[]{-1, 1, k};
        boolean isAble = false;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        isVisited[0][0] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 3; i++) {
                int nx = now[1] + dc[i];
                int ny = now[0];
                int time = now[2];

                // jump
                if (i == 2) {
                    if (now[0] == 1) {
                        ny = 0;
                    } else {
                        ny = 1;
                    }
                }
                if (nx >= N) {
                    isAble = true;
                    break;
                }

                if (nx <= time || isVisited[ny][nx] || map[ny][nx] == 0) {
                    continue;
                }
                isVisited[ny][nx] = true;
                q.add(new int[]{ny, nx, time + 1});

            }
        }
        if (isAble) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
