import java.util.HashSet;
import java.util.Set;

/*
프로그래머스 / 네트워크 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/43162
 */
class PGS_43162 {

    private int[] parent;

    public int solution(int n, int[][] computers) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {  //전체 노드 탐색
            int[] cur = computers[i];

            for (int j = 0; j < n; j++) {
                if (cur[j] == 1) {  //연결된 노드와 합침
                    union(i, j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i : parent) {
            set.add(find(i));  //부모 노드 갱신
        }
        return set.size();
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
}

