package daewonko.pgs;

import java.util.ArrayList;
import java.util.List;

// 프로그래머스 행렬 테두리 회전하기
public class PGS_77485 {
    static int[][] graph;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        graph = new int[rows + 1][columns + 1];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                graph[i][j] = (i - 1) * columns + j;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int min = solve(queries[i]);
            answer[i] = min;
        }
        return answer;


    }

    public static int solve(int[] query) {
        int x1 = query[0], y1 = query[1];
        int x2 = query[2], y2 = query[3];

        List<Integer> list = new ArrayList<>();

        for (int i = y1; i < y2; i++) {
            list.add(graph[x1][i]);
        }

        for (int i = x1; i < x2; i++) {
            list.add(graph[i][y2]);
        }

        for (int i = y2; i > y1; i--) {
            list.add(graph[x2][i]);
        }

        for (int i = x2; i > x1; i--) {
            list.add(graph[i][y1]);
        }

        int min = Integer.MAX_VALUE;
        for (int num : list) {
            min = Math.min(min, num);
        }


        // list에 들어있는 수 회전하기
        list.add(0, list.remove(list.size() - 1));

        int index = 0;

        // 회전한 숫자들 그래프에 다시 넣기
        for (int i = y1; i < y2; i++) {
            graph[x1][i] = list.get(index++);
        }

        for (int i = x1; i < x2; i++) {
            graph[i][y2] = list.get(index++);
        }

        for (int i = y2; i > y1; i--) {
            graph[x2][i] = list.get(index++);

        }

        for (int i = x2; i > x1; i--) {
            graph[i][y1] = list.get(index++);

        }

        return min;

    }

}
