package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1260 {
    static int n,m,v;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean [] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());


        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

//        for (int i = 1; i <= n; i++) {
//            Collections.sort(list.get(i));
//        }

        list.forEach(Collections::sort);

        visited = new boolean[n + 1];
        dfs(v);
        System.out.println(sb);

        sb = new StringBuilder();
        visited = new boolean[n + 1];
        bfs(v);
        System.out.println(sb);


    }

    public static void dfs(int start) {
        visited[start] = true;
        sb.append(start).append(" ");

        for (int i = 0; i < list.get(start).size(); i++) {
            int next = list.get(start).get(i);
            if (!visited[next]) {
                dfs(next);
            }
        }

    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            sb.append(poll).append(" ");
            for (int i = 0; i < list.get(poll).size(); i++) {
                Integer next = list.get(poll).get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }

        }

    }
}
