import java.util.ArrayList;
import java.util.List;

/*
프로그래머스 / 표 병합 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/150366
 */
class PGS_150366 {

    int SIZE = 50 * 50 + 1;
    int[] parent = new int[SIZE];
    String[] value = new String[SIZE];

    public List<String> solution(String[] commands) {
        for (int i = 1; i < SIZE; i++) {
            parent[i] = i;
            value[i] = "";
        }

        List<String> answer = new ArrayList<>();

        for (String s : commands) {
            String[] split = s.split(" ");
            String command = split[0];

            if (command.equals("UPDATE")) {
                if (split.length == 4) {  //"UPDATE r c value"
                    int r = Integer.parseInt(split[1]);
                    int c = Integer.parseInt(split[2]);
                    String val = split[3];

                    //2차원을 1차원으로 표현
                    //(1, 1) -> 1
                    //(2, 1) -> 51
                    int idx = (r - 1) * 50 + c;
                    int root = find(idx);
                    value[root] = val;
                } else {  //"UPDATE value1 value2"
                    String val1 = split[1];
                    String val2 = split[2];

                    for (int i = 1; i < SIZE; i++) {
                        if (value[i].equals(val1)) {
                            value[i] = val2;
                        }
                    }
                }
            } else if (command.equals("MERGE")) {  //"MERGE r1 c1 r2 c2"
                int r1 = Integer.parseInt(split[1]);
                int c1 = Integer.parseInt(split[2]);
                int r2 = Integer.parseInt(split[3]);
                int c2 = Integer.parseInt(split[4]);

                int idx1 = (r1 - 1) * 50 + c1;
                int idx2 = (r2 - 1) * 50 + c2;
                union(idx1, idx2);
            } else if (command.equals("UNMERGE")) {  //"UNMERGE r c"
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                int idx = (r - 1) * 50 + c;
                int root = find(idx);
                String val = value[root];

                //병합을 해제할 셀 탐색
                List<Integer> toUnmerge = new ArrayList<>();
                for (int i = 1; i < SIZE; i++) {
                    if (find(i) == root) {
                        toUnmerge.add(i);
                    }
                }

                for (int i : toUnmerge) {
                    parent[i] = i;
                    value[i] = "";
                }
                value[idx] = val;
            } else if (command.equals("PRINT")) {  //"PRINT r c"
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                int idx = (r - 1) * 50 + c;
                int root = find(idx);
                String val = value[root];

                answer.add(val.isEmpty() ? "EMPTY" : val);
            }
        }

        return answer;
    }

    int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }

        if (value[a].isEmpty()) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}
