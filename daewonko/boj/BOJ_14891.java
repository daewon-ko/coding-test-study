package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 톱니바퀴
public class BOJ_14891 {
    static int [][] wheels;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        wheels = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = line.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int wheelNumber = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());


            // 각 톱니바퀴의 회전방향을 저장하는 배열
            int[] rotate = new int[4];

            rotate[wheelNumber] = direction;


            for (int j = wheelNumber - 1; j >= 0; j--) {
                if (wheels[j][2] != wheels[j + 1][6]) {
                    rotate[j] = -rotate[j + 1];
                } else {
                    break;
                }
            }

            for (int j = wheelNumber + 1; j < 4; j++) {
                if (wheels[j - 1][2] != wheels[j][6]) {
                    rotate[j] = -rotate[j - 1];

                }
                else {
                    break;
                }
            }

            // 회전방향에 따라서 회전

            for (int j = 0; j < 4; j++) {
                if (rotate[j] == 1) {
                    wheels[j] = moveClockWay(wheels[j]);
                } else if (rotate[j] == -1) {
                    wheels[j] = moveReverseClockWay(wheels[j]);
                }
            }


        }

        int answer = 0;
        if(wheels[0][0]==1) answer += 1;
        if (wheels[1][0]==1) answer += 2;
        if (wheels[2][0]==1) answer += 4;
        if (wheels[3][0]==1) answer+=8;

        System.out.println(answer);





    }

    public static int[] moveClockWay(int[] wheel) {

        int[] rotated = new int[8];

        rotated[0] = wheel[7];
        for (int j = 1; j < 8; j++) {
            rotated[j] = wheel[j - 1];
        }

        return rotated;
    }

    public static int[] moveReverseClockWay(int[] wheel) {

        int[] rotated = new int[8];
        for (int j = 0; j < 7; j++) {
            rotated[j] = wheel[j + 1];
        }
        rotated[7] = wheel[0];

        return rotated;

    }

}
