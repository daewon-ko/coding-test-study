package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055 {
    static int N;
    static int K;
    static int[] belt;
    static int[] robots;
    static int zeroDurabilityNum;

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[2 * N];
        robots = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
            if (belt[i] == 0) {
                zeroDurabilityNum++;
            }
        }

        int finishLevel = 0;
        while (zeroDurabilityNum < K) {
            finishLevel++;

            // 1) 컨테이너 벨트와 로봇을 함께 한 칸씩 회전 (내구성 닳지 않음)
            rotateBelt();
            rotateRobot();

            // 2) 가장 먼저 벨트에 올라간 로봇부터 회전 방향으로 한 칸 이동
            moveRobot();

            // 3) 올리는 위치 내구도가 0이 아니면 로봇 올리기
            if (belt[0] > 0) {
                robots[0] = 1;
                belt[0]--;
                if (belt[0] == 0) {
                    zeroDurabilityNum++;
                }
            }
        }
        System.out.println(finishLevel);
    }

    static void rotateBelt() {
        int last = belt[N * 2 - 1];
        for (int i = 2 * N - 2; i >= 0; i--) {
            belt[i + 1] = belt[i];
        }
        belt[0] = last;
    }

    static void rotateRobot() {
        for (int i = N - 2; i >= 0; i--) {
            robots[i + 1] = robots[i];
        }
        robots[0] = 0;
        robots[N - 1] = 0;
    }

    static void moveRobot() {
        for (int i = N - 2; i >= 0; i--) {
            if (robots[i] == 1 && robots[i + 1] == 0 && belt[i + 1] > 0) {
                robots[i + 1] = 1;
                robots[i] = 0;
                belt[i + 1]--;
                if (belt[i + 1] == 0) {
                    zeroDurabilityNum++;
                }
            }
        }
        robots[N - 1] = 0;

    }
}
