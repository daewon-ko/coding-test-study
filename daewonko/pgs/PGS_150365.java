package daewonko.pgs;

import java.util.*;

// 프로그래머스 미로 탈출 명령어
public class PGS_150365 {
    static int depth;
    static List<String> list = new ArrayList<>();
    static int n, m;

    static int[] dy = {0, 1, 0, -1};   // 동, 남, 서, 북
    static int[] dx = {1, 0, -1, 0};
    static String[] direction = {"d", "l", "r", "u"};
    static String answer = null;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {


        this.n = n;
        this.m = m;

        depth = k;


        int minDist = Math.abs(r - x) + Math.abs(c - y);

        if (minDist > k || (k - minDist) % 2 == 1) {
            return "impossible";
        }

        dfs(y - 1, x - 1, c - 1, r - 1, 0, new StringBuilder());


        // 정렬
        Collections.sort(list);

        if (list.isEmpty()) {
            return "impossible";

        }


        return (answer == null) ? "impossible" : answer;


    }

    public static void dfs(int y, int x, int c, int r, int cnt, StringBuilder sb) {
        if (answer != null) return;

        if (cnt == depth) {
            if (y == c && x == r) {
                list.add(sb.toString());
            }

            return;
        }

        int remainingDist = Math.abs(c - y) + Math.abs(r - x);
        if (cnt + remainingDist > depth) return;

        for (int i = 0; i < 4; i++) {
            int newY = y + dy[i];
            int newX = x + dx[i];
            if (inRange(newY, newX)) {
                sb.append(direction[i]);
                dfs(newY, newX, c, r, cnt + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }


    }

    public static boolean inRange(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }

}


