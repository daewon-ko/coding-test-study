import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 색종이 붙이기 / 골드2
https://www.acmicpc.net/problem/17136
 */
public class BOJ_17136 {

    private static final int PAPER_SIZE = 10;
    private static int[][] paper = new int[PAPER_SIZE][PAPER_SIZE];
    private static int[] paperCount = {0, 5, 5, 5, 5, 5};

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < PAPER_SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < PAPER_SIZE; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    private static void recur(int r, int c, int count) {
        if (r == PAPER_SIZE) {  //모든 행을 확인한 경우
            min = Math.min(min, count);
            return;
        }

        if (c == PAPER_SIZE) {  //한 행을 다 확인한 경우 다음 행으로 이동
            recur(r + 1, 0, count);
            return;
        }

        if (paper[r][c] == 0) {  //0인 칸은 패스
            recur(r, c + 1, count);
            return;
        }

        for (int size = 5; size > 0; size--) {  //크기가 5인 종이부터 확인
            if (canPlace(r, c, size) && paperCount[size] > 0) {
                placePaper(r, c, size, 0);  //종이 붙이기
                paperCount[size]--;
                recur(r, c + 1, count + 1);
                placePaper(r, c, size, 1);  //종이 떼기
                paperCount[size]++;
            }
        }
    }

    private static boolean canPlace(int r, int c, int size) {
        if (r + size > PAPER_SIZE || c + size > PAPER_SIZE) {
            return false;
        }

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (paper[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void placePaper(int r, int c, int size, int value) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                paper[i][j] = value;
            }
        }
    }
}
