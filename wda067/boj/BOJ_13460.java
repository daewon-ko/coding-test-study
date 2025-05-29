import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 구슬 탈출 2 / 골드1
https://www.acmicpc.net/problem/13460
 */
public class BOJ_13460 {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    private static int N, M, answer = Integer.MAX_VALUE;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        int[] red = new int[2];
        int[] blue = new int[2];

        //. -> 빈칸, # -> 벽, O -> 구멍, R -> 빨간 구슬, B -> 파란 구슬
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                board[i][j] = c;

                if (c == 'R') {
                    red = new int[]{i, j};
                    board[i][j] = '.';
                } else if (c == 'B') {
                    blue = new int[]{i, j};
                    board[i][j] = '.';
                }
            }
        }

        move(0, red, blue);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void move(int count, int[] red, int[] blue) {
        if (count > 10) {  //10번이 넘을 경우 중단
            return;
        }

        if (board[red[0]][red[1]] == 'O') {  //빨간 공이 구멍에 들어갈 경우 종료
            answer = Math.min(answer, count);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int rr = red[0];
            int rc = red[1];
            int br = blue[0];
            int bc = blue[1];

            int rMove = 0, bMove = 0;
            boolean blueInHole = false;

            while (true) {
                rr += dr[d];
                rc += dc[d];
                rMove++;

                if (board[rr][rc] == '#') {  //벽일 경우 중단
                    rr -= dr[d];
                    rc -= dc[d];
                    rMove--;
                    break;
                }

                if (board[rr][rc] == 'O') {  //구멍에 들어갈 경우 중단
                    break;
                }
            }

            while (true) {
                br += dr[d];
                bc += dc[d];
                bMove++;

                if (board[br][bc] == '#') {  //벽일 경우 중단
                    br -= dr[d];
                    bc -= dc[d];
                    bMove--;
                    break;
                }

                if (board[br][bc] == 'O') {  //구멍에 들어갈 경우 중단
                    blueInHole = true;
                    break;
                }
            }

            if (blueInHole) {  //파란 공이 구멍에 들어갔으면 스킵
                continue;
            }

            if (rr == br && rc == bc) {
                if (rMove > bMove) {
                    rr -= dr[d];
                    rc -= dc[d];
                } else {
                    br -= dr[d];
                    bc -= dc[d];
                }
            }

            move(count + 1, new int[]{rr, rc}, new int[]{br, bc});
        }
    }
}
