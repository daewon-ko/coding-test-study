package daewonko.boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499 {


        static int[] dice = new int[7];
        static int n, m, x, y;
        static int[][] map;

        // 동, 서, 북, 남
        static int[] dx = {1, -1, 0, 0};
        static int[] dy = {0, 0, -1, 1};

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken()); // 주사위의 초기 행 위치
            x = Integer.parseInt(st.nextToken()); // 주사위의 초기 열 위치
            int k = Integer.parseInt(st.nextToken());

            map = new int[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                int d = Integer.parseInt(st.nextToken()) - 1; // 1~4를 0~3으로 변환
                move(d); // 명령 수행
            }
        }

        static void move(int d) {
            // 새로운 좌표 계산
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 지도 범위를 벗어나면 명령 무시
            if (!inRange(nx, ny)) return;

            // 주사위 회전
            roll(d);

            // 좌표 갱신
            x = nx;
            y = ny;

            // 지도와 주사위 값 교환
            if (map[ny][nx] == 0) {
                map[ny][nx] = dice[6]; // 주사위 아랫면 값을 지도에 복사
            } else {
                dice[6] = map[ny][nx]; // 지도 값을 주사위 아랫면에 복사
                map[ny][nx] = 0;      // 지도 값을 0으로 변경
            }

            // 주사위 윗면 출력
            System.out.println(dice[3]);
        }

        static void roll(int d) {
            int temp = dice[3];
            switch (d) {
                case 0: // 동
                    dice[3] = dice[4];
                    dice[4] = dice[6];
                    dice[6] = dice[2];
                    dice[2] = temp;
                    break;
                case 1: // 서
                    dice[3] = dice[2];
                    dice[2] = dice[6];
                    dice[6] = dice[4];
                    dice[4] = temp;
                    break;
                case 2: // 북
                    dice[3] = dice[5];
                    dice[5] = dice[6];
                    dice[6] = dice[1];
                    dice[1] = temp;
                    break;
                case 3: // 남
                    dice[3] = dice[1];
                    dice[1] = dice[6];
                    dice[6] = dice[5];
                    dice[5] = temp;
                    break;
            }
        }

        static boolean inRange(int nx, int ny) {
            // 범위를 벗어나지 않으면 true 반환
            return nx >= 0 && ny >= 0 && nx < m && ny < n;
        }
    }


