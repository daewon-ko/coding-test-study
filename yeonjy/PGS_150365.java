package yeonjy.pgs;

public class PGS_150365 {
    static final int[] dx = new int[]{1, 0, 0, -1};
    static final int[] dy = new int[]{0, -1, 1, 0};

    static int n;
    static int m;
    static int r;
    static int c;
    static int k;
    static boolean isSuccess;
    static String answer;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;

        answer = "impossible";
        dfs(x, y, "", 0);
        return answer;
    }

    static void dfs(int x, int y, String way, int depth) {
        if (isSuccess) {
            return;
        }
        int distance = getDistance(x, y);
        if (k - depth < distance) {
            return;
        }
        if ((k - depth - distance) % 2 == 1) {
            return;
        }
        if (depth == k && x == r && y == c) {
            answer = way;
            isSuccess = true;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            char newWay = getWay(i);
            if (nx >= 1 && ny >= 1 && nx <= n && ny <= m) {
                dfs(nx, ny, way + newWay, depth + 1);
            }
        }
    }

    static int getDistance(int x, int y) {
        return Math.abs(x - r) + Math.abs(y - c);
    }

    static char getWay(int i) {
        if (i == 0) {
            return 'd';
        }
        if (i == 1) {
            return 'l';
        }
        if (i == 2) {
            return 'r';
        }
        return 'u';
    }
}
