import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
백준 / 트리 / 골드4
https://www.acmicpc.net/problem/4803
 */
public class BOJ_4803 {

    private static int n, m;
    private static int[] parent;
    private static boolean[] hasCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int index = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());  //정점의 수
            m = Integer.parseInt(st.nextToken());  //간선의 수
            if (n == 0 && m == 0) {
                break;
            }

            parent = new int[n + 1];
            hasCycle = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                int root = find(i);
                if (!hasCycle[root]) {
                    set.add(root);
                }
            }

            sb.append("Case ").append(index++).append(": ");

            if (set.size() > 1) {
                sb.append("A forest of ").append(set.size()).append(" trees.");
            } else if (set.size() == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("No trees.");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {  //사이클 발생
            hasCycle[a] = true;
        } else {
            if (a < b) {
                parent[b] = a;
                hasCycle[a] |= hasCycle[b];
            } else {
                parent[a] = b;
                hasCycle[b] |= hasCycle[a];
            }
        }
    }
}
