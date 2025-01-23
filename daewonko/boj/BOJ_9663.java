package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663 {
    static int n;
    static boolean[][] visited;

    static int[] dy = {-1, -1};
    static int[] dx = {-1, 1};

    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1][n + 1];

        dfs(1);

        System.out.println(cnt);


    }

    public static void dfs(int y) {

        if (y == n + 1) {
            cnt++;
            return;
        }

        for (int x = 1; x <= n; x++) {
            if (canPlace(y, x)) {
                visited[y][x] = true;
                dfs(y + 1);
                visited[y][x] = false;
            }
        }





    }

    public static boolean canPlace(int y, int x) {

        // 동일 열 확인
        for (int i = 1; i <= n; i++) {
            if (visited[i][x]) {
                return false;
            }
        }

        for (int d = 0; d < 2; d++) {

            int newY = y + dy[d];
            int newX = x + dx[d];

            while (inRange(newY, newX)) {
                if (visited[newY][newX]) {
                    return false;
                }

                newY += dy[d];
                newX += dx[d];
            }
        }

//        // 좌상 확인
//        for (int i = 1; y - i >= 0 && x - i >= 0; i++) {
//            if (visited[y - i][x - i]) {
//                return false;
//            }
//        }
//        // 우상 확인
//        for (int i = 1; y - i >= 0 && x + i <= n; i++) {
//            if (visited[y - i][x + i]) {
//                return false;
//            }
//        }

        return true;


    }

    public static boolean inRange(int y, int x) {
        return y >= 1 && y <= n && x >= 1 && x <= n;
    }

}

