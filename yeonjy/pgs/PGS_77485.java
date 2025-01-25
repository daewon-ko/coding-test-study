package yeonjy.pgs;

public class PGS_77485 {
    static int[][] map;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        // 행렬 초기화
        map = new int[rows + 1][columns + 1];
        int now = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = now++;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(queries[i]);
        }

        return answer;
    }

    static int rotate(int[] query) {
        int min = Integer.MAX_VALUE;

        // 위
        int up = map[query[0]][query[3]];
        min = Math.min(min, up);
        for (int i = query[3]; i > query[1]; i--) {
            int now = map[query[0]][i - 1];
            map[query[0]][i] = now;
            min = Math.min(min, now);
        }


        // 오른쪽
        int right = map[query[2]][query[3]];
        min = Math.min(min, right);
        for (int i = query[2]; i > query[0]; i--) {
            int now = map[i - 1][query[3]];
            map[i][query[3]] = now;
            min = Math.min(min, now);
        }
        map[query[0] + 1][query[3]] = up;


        // 아래
        int down = map[query[2]][query[1]];
        min = Math.min(min, down);
        for (int i = query[1]; i < query[3]; i++) {
            int now = map[query[2]][i + 1];
            map[query[2]][i] = now;
            min = Math.min(min, now);
        }
        map[query[2]][query[3] - 1] = right;


        // 왼쪽
        for (int i = query[0]; i < query[2]; i++) {
            int now = map[i + 1][query[1]];
            map[i][query[1]] = now;
            min = Math.min(min, now);
        }
        map[query[2] - 1][query[1]] = down;

        return min;
    }
}
