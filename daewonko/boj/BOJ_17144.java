package daewonko.boj;
// 백준 미세먼지 안녕
import java.util.*;
public class BOJ_17144 {


        static int R, C, T;
        static int[][] map;
        static int[][] temp;
        static int[] cleaner = new int[2];
        static int[] dx = { -1, 1, 0, 0 };
        static int[] dy = { 0, 0, -1, 1 };

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            R = sc.nextInt();
            C = sc.nextInt();
            T = sc.nextInt();

            map = new int[R][C];
            temp = new int[R][C];

            int cleanerIndex = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] == -1) {
                        cleaner[cleanerIndex++] = i;
                    }
                }
            }

            while (T-- > 0) {
                spreadDust();
                operateCleaner();
            }

            System.out.println(getTotalDust());
        }

        static void spreadDust() {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        int spreadAmount = map[i][j] / 5;
                        int spreadCount = 0;

                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];

                            if (nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] != -1) {
                                temp[nx][ny] += spreadAmount;
                                spreadCount++;
                            }
                        }
                        temp[i][j] -= spreadAmount * spreadCount;
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    map[i][j] += temp[i][j];
                    temp[i][j] = 0;
                }
            }
        }

        static void operateCleaner() {
            int upper = cleaner[0];
            int lower = cleaner[1];

            // Upper part
            for (int i = upper - 1; i > 0; i--) map[i][0] = map[i - 1][0];
            for (int i = 0; i < C - 1; i++) map[0][i] = map[0][i + 1];
            for (int i = 0; i < upper; i++) map[i][C - 1] = map[i + 1][C - 1];
            for (int i = C - 1; i > 1; i--) map[upper][i] = map[upper][i - 1];
            map[upper][1] = 0;

            // Lower part
            for (int i = lower + 1; i < R - 1; i++) map[i][0] = map[i + 1][0];
            for (int i = 0; i < C - 1; i++) map[R - 1][i] = map[R - 1][i + 1];
            for (int i = R - 1; i > lower; i--) map[i][C - 1] = map[i - 1][C - 1];
            for (int i = C - 1; i > 1; i--) map[lower][i] = map[lower][i - 1];
            map[lower][1] = 0;
        }

        static int getTotalDust() {
            int total = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        total += map[i][j];
                    }
                }
            }
            return total;
        }
    }


