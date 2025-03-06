import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 사회망 서비스(SNS) / 골드3
https://www.acmicpc.net/problem/2533
 */
public class BOJ_2533 {

    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //2 ~ 1,000,000
        visited = new boolean[N + 1];
        //dp[node][0]: node가 얼리 어답터가 아닌 경우, 서브트리에서 필요한 최소 얼리 어답터 수
        //dp[node][1]: node가 얼리 어답터인 경우, 서브트리에서 필요한 최소 얼리 어답터 수
        dp = new int[N + 1][2];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;  //얼리 어답터 X
        dp[node][1] = 1;  //얼리 어답터 O

        for (Integer child : graph.get(node)) {
            if (!visited[child]) {
                dfs(child);
                dp[node][0] += dp[child][1];  //내가 얼리 어답터가 아니면 자식은 얼리 어답터
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);  //내가 얼리 어답터면 자식은 선택 가능
            }
        }
    }
}
