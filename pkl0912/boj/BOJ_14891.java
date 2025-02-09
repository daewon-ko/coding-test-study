package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_14891 {
    static int[][] arr; 
    static int[] directions; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        int n = Integer.parseInt(br.readLine()); 
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1; 
            int dir = Integer.parseInt(st.nextToken()); 

            move(num, dir); 
        }


        int answer = 0;
        if (arr[0][0] == 1) answer += 1;
        if (arr[1][0] == 1) answer += 2;
        if (arr[2][0] == 1) answer += 4;
        if (arr[3][0] == 1) answer += 8;

        System.out.println(answer);
    }

    public static void move(int num, int dir) {
        directions = new int[4];
        directions[num] = dir;

        // 왼쪽 톱니바퀴 회전 여부 체크
        for (int i = num; i > 0; i--) {
            if (arr[i][6] != arr[i - 1][2]) { // 극이 다르면 반대 방향으로 회전
                directions[i - 1] = -directions[i];
            } else break;
        }

        // 오른쪽 톱니바퀴 회전 여부 체크
        for (int i = num; i < 3; i++) {
            if (arr[i][2] != arr[i + 1][6]) { // 극이 다르면 반대 방향으로 회전
                directions[i + 1] = -directions[i];
            } else break;
        }

        // 톱니바퀴 회전 수행
        for (int i = 0; i < 4; i++) {
            if (directions[i] == 1) {
                moveClock(i);
            } else if (directions[i] == -1) {
                moveAntiClock(i);
            }
        }
    }

    public static void moveClock(int idx) {
        int[] newCircle = new int[8];
        for (int i = 0; i < 8; i++) {
            newCircle[(i + 1) % 8] = arr[idx][i]; // 시계 방향 회전
        }
        arr[idx] = newCircle;
    }

    public static void moveAntiClock(int idx) {
        int[] newCircle = new int[8];
        for (int i = 0; i < 8; i++) {
            newCircle[i] = arr[idx][(i + 1) % 8]; // 반시계 방향 회전
        }
        arr[idx] = newCircle;
    }
}
