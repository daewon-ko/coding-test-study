package daewonko.boj;
import java.io.*;
import java.util.*;
public class BOJ_4803 {


        static List<Integer>[] adj;
        static boolean[] visited;
        static boolean isTree;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            int testCase = 1;

            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());

                if (n == 0 && m == 0) break;

                adj = new ArrayList[n + 1];
                visited = new boolean[n + 1];

                for (int i = 1; i <= n; i++) {
                    adj[i] = new ArrayList<>();
                }

                for (int i = 0; i < m; i++) {
                    st = new StringTokenizer(br.readLine());
                    int u = Integer.parseInt(st.nextToken());
                    int v = Integer.parseInt(st.nextToken());
                    adj[u].add(v);
                    adj[v].add(u);
                }

                int treeCount = 0;
                for (int i = 1; i <= n; i++) {
                    if (!visited[i]) {
                        isTree = true;
                        dfs(i, -1);
                        if (isTree) treeCount++;
                    }
                }

                sb.append("Case ").append(testCase++).append(": ");
                if (treeCount == 0) sb.append("No trees.\n");
                else if (treeCount == 1) sb.append("There is one tree.\n");
                else sb.append("A forest of ").append(treeCount).append(" trees.\n");
            }

            System.out.print(sb);
        }

        static void dfs(int node, int parent) {
            visited[node] = true;
            for (int next : adj[node]) {
                if (next == parent) continue;
                if (visited[next]) isTree = false;
                else dfs(next, node);
            }
        }
    }

