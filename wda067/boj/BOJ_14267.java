import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 회사 문화 1 / 골드4
https://www.acmicpc.net/problem/14267
 */
public class BOJ_14267 {

    private static List<List<Integer>> adjList = new ArrayList<>();
    private static int[] compliment;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  //2 ~ 100,000
        int m = Integer.parseInt(st.nextToken());  //2 ~ 100,000

        //직속 상사 저장
        int[] parent = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        //인접 리스트 초기화
        for (int i = 2; i <= n; i++) {
            adjList.get(parent[i]).add(i);
        }

        compliment = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            compliment[num] += w;  //해당 직원의 칭찬 누적
        }

        dfs(1);  //칭찬을 자식 노드로 전파

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(compliment[i]).append(" ");
        }

        System.out.println(sb);
    }

    private static void dfs(int node) {
        for (Integer next : adjList.get(node)) {
            compliment[next] += compliment[node];  //자식 노드에 부모 노드의 값 전파
            dfs(next);
        }
    }
}
