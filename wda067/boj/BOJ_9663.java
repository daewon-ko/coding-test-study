import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
백준 / N-Queen / 골드4
https://www.acmicpc.net/problem/9663
 */
public class BOJ_9663 {

    static int N, count;
    static boolean[] board;
    static boolean[] colCheck = new boolean[14];
    static boolean[] plusDiagonalCheck = new boolean[27];
    static boolean[] minusDiagonalCheck = new boolean[27];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new boolean[N];

        recur(0);  //0번 행부터 시작
        System.out.println(count);
    }

    static void recur(int row) {
        if (row == N) {  //마지막 행까지 퀸을 놓은 경우
            count++;
            return;
        }

        //모든 열에 대해 반복
        for (int col = 0; col < N; col++) {
            if (canPlaceQueen(row, col)) {
                updateQueen(row, col, true);  //퀸 배치
                recur(row + 1);
                updateQueen(row, col, false);  //퀸 제거
            }
        }
    }

    //(row, col)에 퀸 배치 가능 여부 반환
    private static boolean canPlaceQueen(int row, int col) {
        return !colCheck[col] &&  //같은 열에 퀸 존재 여부
                !plusDiagonalCheck[row + col] &&  //양의 기울기인 대각선에 퀸 존재 여부
                !minusDiagonalCheck[row - col + N - 1];  //음의 기울기인 대각선에 퀸 존재 여부
    }

    //(row, col)에 퀸 배치 또는 제거
    private static void updateQueen(int row, int col, boolean state) {
        colCheck[col] = state;
        plusDiagonalCheck[row + col] = state;
        minusDiagonalCheck[row - col + N - 1] = state;
    }
}