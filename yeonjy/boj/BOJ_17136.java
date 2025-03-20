package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17136 {
    static int[][] map = new int[10][10];
    static int[] usedPaperCnt = new int[6];
    static int min = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backtracking(0, 0, 0);
        System.out.println(min);
    }

    static void backtracking(int x, int y, int cnt) {
        if (y > 9) {
            backtracking(x + 1, 0, cnt);
            return;
        }
        if (x > 9) {
            if (min == -1 || min > cnt) {
                min = cnt;
            }
            return;
        }

        if (map[x][y] == 0) {
            backtracking(x, y + 1, cnt);
            return;
        }

        for (int i = 5; i >= 1; i--) {
            if (usedPaperCnt[i] < 5 && x + i <= 10 && y + i <= 10) {
                if (isSquare(x, y, i)) {
                    checkSquare(x, y, i, true);
                    backtracking(x, y + i, cnt + 1);
                    checkSquare(x, y, i, false);
                }
            }
        }
    }

    static void checkSquare(int x, int y, int paper, boolean isUsed) {
        if (isUsed) {
            usedPaperCnt[paper]++;
        } else {
            usedPaperCnt[paper]--;
        }

        int value = 1;
        if (isUsed) {
            value = 0;
        }
        for (int i = 0; i < paper; i++) {
            for (int j = 0; j < paper; j++) {
                map[x + i][y + j] = value;
            }
        }
    }

    static boolean isSquare(int x, int y, int paper) {
        for (int i = 0; i < paper; i++) {
            for (int j = 0; j < paper; j++) {
                if (map[x + i][y + j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
