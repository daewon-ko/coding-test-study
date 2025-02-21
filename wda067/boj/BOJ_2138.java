import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
백준 / 전구와 스위치 / 골드4
https://www.acmicpc.net/problem/2138
 */
public class BOJ_2138 {

    private static int[] cur, desire;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cur = new int[N];
        desire = new int[N];

        char[] charArray = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            cur[i] = charArray[i] - '0';
        }

        char[] charArray2 = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            desire[i] = charArray2[i] - '0';
        }

        int result1 = solve(false);  //첫 번째 스위치를 누르지 않음
        int result2 = solve(true);  //첫 번째 스위치를 누름

        int answer;
        if (result1 == -1) {
            answer = result2;
        } else if (result2 == -1) {
            answer = result1;
        } else {
            answer = Math.min(result1, result2);
        }

        System.out.println(answer);
    }

    static int solve(boolean pressFirst) {
        int[] clone = cur.clone();
        int count = 0;

        if (pressFirst) {
            for (int i = -1; i <= 1; i++) {
                if (i >= 0 && i < N) {
                    clone[i] ^= 1; // 0 -> 1, 1 -> 0 토글
                }
            }
            count++;
        }

        for (int i = 1; i < N; i++) {
            if (clone[i - 1] != desire[i - 1]) { // 이전 전구가 목표 상태와 다르면 눌러야 함
                for (int j = i - 1; j <= i + 1; j++) {
                    if (j >= 0 && j < N) {
                        clone[j] ^= 1; // 0 -> 1, 1 -> 0 토글
                    }
                }
                count++;
            }
        }

        // 마지막 상태가 목표 상태와 같은지 확인
        if (Arrays.equals(clone, desire)) {
            return count;
        }

        return -1;
    }
}