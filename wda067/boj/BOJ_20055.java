import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 컨베이어 벨트 위의 로봇 / 골드5
https://www.acmicpc.net/problem/20055
 */
public class BOJ_20055 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int len = 2 * N;
        int[] A = new int[len + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= len; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] robot = new boolean[N + 1];

        int result = 1;
        while (true) {

            int[] temp = new int[len + 1];
            boolean[] robotTemp = new boolean[N + 1];
            for (int i = 2; i <= len; i++) {
                temp[i] = A[i - 1];  //벨트 회전
                if (i <= N) {
                    robotTemp[i] = robot[i - 1];  //로봇 회전
                }
            }
            temp[1] = A[len];  //2N -> 1
            robotTemp[N] = false;  //로봇이 내리는 위치

            for (int i = N - 1; i > 0; i--) {
                //로봇 이동할 수 있으면 이동
                if (robotTemp[i] && temp[i + 1] > 0 && !robotTemp[i + 1]) {
                    robotTemp[i] = false;
                    robotTemp[i + 1] = true;
                    temp[i + 1]--;
                }
            }

            //올리는 위치에 로봇 올림
            if (temp[1] > 0) {
                robotTemp[1] = true;
                temp[1]--;
            }

            long count = Arrays.stream(temp)
                    .filter(i -> i == 0)
                    .count();
            if (count > K) {
                break;
            }

            robot = robotTemp;
            A = temp;
            result++;
        }

        System.out.println(result);
    }
}
