package daewonko.boj;

import java.io.*;
import java.util.*;
// 회사 문화 1
public class BOJ_14267 {


        static List<Integer>[] tree;
        static int[] praise;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            tree = new ArrayList[n + 1];
            praise = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                tree[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int parent = Integer.parseInt(st.nextToken());
                if (parent != -1) {
                    tree[parent].add(i);
                }
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int employee = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                praise[employee] += w;
            }

            dfs(1);

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                sb.append(praise[i]).append(" ");
            }
            System.out.println(sb);
        }

        static void dfs(int node) {
            for (int child : tree[node]) {
                praise[child] += praise[node];
                dfs(child);
            }
        }
    }

