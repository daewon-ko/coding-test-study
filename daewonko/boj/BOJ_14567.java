package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 선수과목

public class BOJ_14567{

    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] degree = new int[n + 1];
        int[] semester = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(pre).add(end);
            degree[end]++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 1학기에 바로 수강할 수 있는 것들만 pq에 들어간다.
        // 즉, 선수과목이 없는 것들
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                pq.add(i);
                semester[i] = 1;
            }
        }

        while (!pq.isEmpty()) {
            // 1학기에
            int current = pq.poll();

            for (int next : graph.get(current)) {
                degree[next]--;

                semester[next] = Math.max(semester[next], semester[current] + 1);
                
                if (degree[next] == 0) {
                    pq.add(next);
                }
            }

        }

        for (int i = 1; i <= n; i++) {
            System.out.print(semester[i] + " ");
        }



    }
}
