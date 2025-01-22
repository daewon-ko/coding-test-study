package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 LCA -
class BOJ_11437_FAIL {
    static int n, m;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static List<Integer> listA = new ArrayList<>();
    static List<Integer> listB = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());


        for (int i = 1; i <= n + 1; i++) {
            list.add(new ArrayList<>());
        }

        // 양방향 노드 연결
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(list.get(i));
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            visited = new boolean[n + 1];
            listA = new ArrayList<>();
            listB = new ArrayList<>();
            LCA(1, a, listA);
            LCA(1, b, listB);

            // 공통 조상 찾기
            int result = 0;
            for (int j = listA.size() - 1; j >= 0; j--) {
                if (listB.contains(listA.get(j))) {
                    result = listA.get(j);
                    break;
                }
            }

            System.out.println(result);


        }

    }

    public static void LCA(int start, int destination, List<Integer> vistedList) {


        visited[start] = true;

        if (start == destination) {
            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    vistedList.add(i);
                }
            }

            return;
        }

        List<Integer> linkedNodeList = list.get(start);

        for (int i = 0; i < linkedNodeList.size(); i++) {
            int linkedNode = linkedNodeList.get(i);
            if (!visited[linkedNode]) {
                LCA(linkedNode, destination, vistedList);
                visited[linkedNode] = false;
            }
        }


    }
}


public class BOJ_11437 {
    static int n, log;
    static List<List<Integer>> tree = new ArrayList<>();
    static int[][] parent;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 트리 초기화
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        // log 계산 (최대 높이)
        log = (int) Math.ceil(Math.log(n) / Math.log(2));

        // 초기화
        parent = new int[n + 1][log + 1];
        depth = new int[n + 1];

        // DFS로 깊이와 첫 번째 부모 설정
        dfs(1, 0);

        // DP 테이블 초기화
        buildSparseTable();

        // 쿼리 처리
        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findLCA(a, b)).append("\n");
        }
        System.out.print(sb);
    }

    // DFS로 깊이와 첫 번째 부모 설정
    static void dfs(int current, int parentNode) {
        depth[current] = depth[parentNode] + 1;
        parent[current][0] = parentNode;

        for (int next : tree.get(current)) {
            if (next != parentNode) {
                dfs(next, current);
            }
        }
    }

    // Sparse Table(DP 테이블) 구축
    static void buildSparseTable() {
        for (int j = 1; j <= log; j++) {
            for (int i = 1; i <= n; i++) {
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
            }
        }
    }

    // LCA 찾기
    static int findLCA(int a, int b) {
        // 깊이 맞추기
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = log; i >= 0; i--) {
            if (depth[a] - depth[b] >= (1 << i)) {
                a = parent[a][i];
            }
        }

        if (a == b) {
            return a;
        }

        // 공통 조상 찾기
        for (int i = log; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
}

