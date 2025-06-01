import java.util.ArrayList;
import java.util.List;

/*
프로그래머스 / 표 병합 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/150366
 */
class PGS_150366 {

    private static final int SIZE = 50;
    private static final int TOTAL = SIZE * SIZE;
    private int[] parent = new int[TOTAL];
    private String[] values = new String[TOTAL];

    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        for (String cmd : commands) {
            String[] parts = cmd.split(" ");
            String action = parts[0];

            switch (action) {
                case "UPDATE":
                    if (parts.length == 4) {
                        int r = Integer.parseInt(parts[1]) - 1;
                        int c = Integer.parseInt(parts[2]) - 1;
                        String value = parts[3];
                        int idx = r * SIZE + c;
                        int root = find(idx);
                        values[root] = value;
                    } else {
                        String oldValue = parts[1];
                        String newValue = parts[2];
                        for (int i = 0; i < TOTAL; i++) {
                            if (values[i].equals(oldValue)) {
                                values[i] = newValue;
                            }
                        }
                    }
                    break;

                case "MERGE":
                    int r1 = Integer.parseInt(parts[1]) - 1;
                    int c1 = Integer.parseInt(parts[2]) - 1;
                    int r2 = Integer.parseInt(parts[3]) - 1;
                    int c2 = Integer.parseInt(parts[4]) - 1;
                    int idx1 = r1 * SIZE + c1;
                    int idx2 = r2 * SIZE + c2;
                    union(idx1, idx2);
                    break;

                case "UNMERGE":
                    int r = Integer.parseInt(parts[1]) - 1;
                    int c = Integer.parseInt(parts[2]) - 1;
                    int idx = r * SIZE + c;
                    int root = find(idx);
                    String val = values[root];
                    List<Integer> toReset = new ArrayList<>();
                    for (int i = 0; i < TOTAL; i++) {
                        if (find(i) == root) {
                            toReset.add(i);
                        }
                    }
                    for (int i : toReset) {
                        parent[i] = i;
                        values[i] = "";
                    }
                    values[idx] = val;
                    break;

                case "PRINT":
                    r = Integer.parseInt(parts[1]) - 1;
                    c = Integer.parseInt(parts[2]) - 1;
                    idx = r * SIZE + c;
                    root = find(idx);
                    String result = values[root];
                    answer.add(result.isEmpty() ? "EMPTY" : result);
                    break;
            }
        }

        return answer.toArray(new String[0]);
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }

        //병합 시 우선순위: 값이 있는 셀을 대표로 설정
        if (!values[rootX].isEmpty()) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
        }
    }
}
