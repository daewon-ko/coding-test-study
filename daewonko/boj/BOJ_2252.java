package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백준 줄 세우기
public class BOJ_2252 {
    static int n, m;
    static Queue<Integer> queue = new LinkedList<>();
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n+1; i++) {
            graph.add(new ArrayList<>());
        }

        degree = new int[n + 1];


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);

            degree[b]++;

        }

        // 차수가 0인 노드 Queue에 삽입
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            sb.append(current).append(" ");

            for (int nextNode : graph.get(current)) {
                degree[nextNode]--;
                if (degree[nextNode] == 0) {
                    queue.add(nextNode);
                }
            }
        }



        System.out.println(sb);


    }
}
