import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 이분 그래프 / 골드4
https://www.acmicpc.net/problem/1707
 */
public class BOJ_1707 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < K; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());  //20,000
            int E = Integer.parseInt(st.nextToken());  //200,000

            List<List<Integer>> adjList = new ArrayList<>();
            int[] num = new int[V + 1];

            for (int i = 0; i <= V; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }

            boolean flag = false;
            for (int i = 1; i <= V; i++) {
                if (num[i] != 0) {
                    continue;
                }

                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                num[i] = 1;

                while (!q.isEmpty()) {
                    int cur = q.poll();

                    for (int next : adjList.get(cur)) {
                        if (num[next] == 0) {
                            num[next] = num[cur] * -1;  //인접 노드 집합 구분
                            q.add(next);
                        } else {
                            if (num[next] == num[cur]) {  //인접 노드의 집합이 같을 경우
                                flag = true;
                            }
                        }
                    }
                }
            }

            if (flag) {
                sb.append("NO").append("\n");
            } else {
                sb.append("YES").append("\n");
            }
        }

        System.out.println(sb);
    }
}
