import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 드래곤 커브 / 골드3
https://www.acmicpc.net/problem/15685
 */
public class BOJ_15685 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[100][100];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<Integer> directions = new ArrayList<>();
            directions.add(d);

            //세대 수만큼 드래곤 커브 생성
            for (int j = 0; j < g; j++) {
                for (int k = directions.size() - 1; k >= 0; k--) {
                    int nextD = (directions.get(k) + 1) % 4;
                    directions.add(nextD);
                }
            }

            map[y][x] = true;
            for (int dir : directions) {
                x += dx[dir];
                y += dy[dir];
                if (x >= 0 && x <= 100 && y >= 0 && y <= 100) {
                    map[y][x] = true;
                }
            }
        }

        int count = 0;

        //사각형 카운트
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] &&
                        map[i + 1][j] && map[i + 1][j + 1]) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
