import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 우수 마을 / 골드2
https://www.acmicpc.net/problem/1949
 */
public class BOJ_1949 {

    private static List<List<Integer>> adjList = new ArrayList<>();
    private static int[] people;
    private static int[][] dp;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //10,000
        people = new int[N + 1];
        //dp[node][0]: node를 우수 마을로 선정하지 않았을 때 최대 인구 수
        //dp[node][1]: node를 우수 마을로 선정했을 떄 최대 인구 수
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adjList.get(A).add(B);
            adjList.get(B).add(A);
        }

        dfs(1);  //1번을 루트 노드로 설정
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;             //node를 선정하지 않았을 때
        dp[node][1] = people[node];  //node를 선정했을 때

        for (Integer child : adjList.get(node)) {
            if (!visited[child]) {  //자식 노드 탐색
                dfs(child);

                //자식 노드가 선정될 수도있고 안될 수도 있음
                dp[node][0] += Math.max(dp[child][0], dp[child][1]);
                //자식 노드를 선정하지 않은 경우만 더함 -> 우수 마을은 인접 X
                dp[node][1] += dp[child][0];
            }
        }
    }
}
