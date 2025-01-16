package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 케빈 베이컨의 6단계 법칙
public class BOJ_1389 {
    static int n, m;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static int MIN = Integer.MAX_VALUE;
    static final int MAX = 100 + 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 1부터 n까지의 node를 만들어준다.
        for (int i = 1; i <= MAX; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }


        int result = 0;
        for (int i = 1; i <= n; i++) {
            int sum = bfs(i);
            if (sum < MIN) {
                MIN = sum;
                result = i;
            }

        }
        System.out.println(result);


    }

    public static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : list.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1;
                    queue.offer(neighbor);
                }
            }
        }


        int sum = 0;
        for (int i = 0; i < distance.length; i++) {
            sum += distance[i];
        }
        return sum;
    }

}





