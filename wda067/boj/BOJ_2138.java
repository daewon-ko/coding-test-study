import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
백준 / 전구와 스위치 / 골드4
https://www.acmicpc.net/problem/2138
 */
public class BOJ_2138 {

    private static int[] current, desired;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  //100,000 -> O(n^2) 불가
        current = new int[N];
        desired = new int[N];

        char[] charArray = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            current[i] = charArray[i] - '0';
        }

        char[] charArray1 = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            desired[i] = charArray1[i] - '0';
        }

        int turnOnFirst = solve(true);
        int turnOffFirst = solve(false);

        int answer;
        if (turnOnFirst == -1) {
            answer = turnOffFirst;
        } else if (turnOffFirst == -1) {
            answer = turnOnFirst;
        } else {
            answer = Math.min(turnOnFirst, turnOffFirst);
        }

        System.out.println(answer);
    }

    //전구의 상태를 바꾸면 좌우 전구까지 영향을 미치므로 순차적으로 해결해야 한다.
    private static int solve(boolean isFirstTurnedOn) {
        int[] clone = current.clone();
        int count = 0;

        if (isFirstTurnedOn) {
            clone[0] ^= 1;
            clone[1] ^= 1;
            count++;
        }

        for (int i = 1; i < N; i++) {  //두 번째 전구부터 탐색
            if (clone[i - 1] != desired[i - 1]) {  //이전 전구가 목표와 다를 경우 스위치를 누른다.

                if (i == N - 1) {  //마지막 전구일 경우
                    clone[N - 1] ^= 1;
                    clone[N - 2] ^= 1;
                } else {
                    for (int j = i - 1; j <= i + 1; j++) {
                        clone[j] ^= 1;
                    }
                }

                count++;
            }
        }

        //마지막 상태가 목표 상태와 같은지 확인
        if (Arrays.equals(clone, desired)) {
            return count;
        }

        return -1;
    }
}
