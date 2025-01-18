package yeonjy.boj;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14890 {
    static int N;
    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        // 가로
        for (int i = 0; i < N; i++) {
            int[] forward = map[i];
            answer += isRoad(forward);
        }

        // 세로
        for (int j = 0; j < N; j++) {
            int[] forward = new int[N];
            for (int i = 0; i < N; i++) {
                forward[i] = map[i][j];
            }
            answer += isRoad(forward);
        }

        System.out.println(answer);
    }

    private static int isRoad(int[] forward) {
        if (isPossible(forward)) {
            return 1;
        }
        return 0;
    }

    private static boolean isPossible(int[] heights) {
        int h = heights[0];
        int len = 1;
        boolean[] incline = new boolean[N];

        for (int i = 1; i < N; i++) {
            if (heights[i] == h) {
                len++;
            } else if (heights[i] - h == 1) {
                if (len >= L) {
                    for (int j = i - L; j < i; j++) {
                        if (incline[j]) {
                            return false;
                        }
                    }
                    h = heights[i];
                    len = 1;
                } else {
                    return false;
                }
            } else if (h - heights[i] == 1) {
                if (i + (L - 1) < N) {
                    for (int j = i + 1; j < i + (L); j++) {
                        if (heights[i] != heights[j] || incline[j]) {
                            return false;
                        }
                    }
                    Arrays.fill(incline, i + 1, i + L, true);
                    if (L == 1) {
                        incline[i] = true;
                    }

                    h = heights[i];
                    len = 1;
                    i += L - 1;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}