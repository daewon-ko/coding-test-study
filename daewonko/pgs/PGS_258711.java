package daewonko.pgs;

import java.util.*;
public class PGS_258711 {


        static int[][] parent;
        static int[] depth;
        static List<Integer>[] tree;
        static int logN;

        public int[] solution(int[][] edges, int[][] queries) {
            int n = edges.length + 1;
            logN = (int) (Math.log(n) / Math.log(2)) + 1;
            parent = new int[n + 1][logN];
            depth = new int[n + 1];
            tree = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();
            for (int[] edge : edges) {
                tree[edge[0]].add(edge[1]);
                tree[edge[1]].add(edge[0]);
            }

            dfs(1, 0);
            preprocess(n);

            int[] result = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int u = queries[i][0], v = queries[i][1];
                result[i] = distance(u, v);
            }
            return result;
        }

        static void dfs(int node, int par) {
            for (int next : tree[node]) {
                if (next == par) continue;
                depth[next] = depth[node] + 1;
                parent[next][0] = node;
                dfs(next, node);
            }
        }

        static void preprocess(int n) {
            for (int j = 1; j < logN; j++) {
                for (int i = 1; i <= n; i++) {
                    if (parent[i][j - 1] != 0)
                        parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }
        }

        static int lca(int u, int v) {
            if (depth[u] < depth[v]) {
                int temp = u;
                u = v;
                v = temp;
            }
            for (int i = logN - 1; i >= 0; i--) {
                if (depth[u] - (1 << i) >= depth[v])
                    u = parent[u][i];
            }
            if (u == v) return u;
            for (int i = logN - 1; i >= 0; i--) {
                if (parent[u][i] != parent[v][i]) {
                    u = parent[u][i];
                    v = parent[v][i];
                }
            }
            return parent[u][0];
        }

        static int distance(int u, int v) {
            int ancestor = lca(u, v);
            return (depth[u] - depth[ancestor]) + (depth[v] - depth[ancestor]);
        }
    }

