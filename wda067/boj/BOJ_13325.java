import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 이진 트리 / 골드3
https://www.acmicpc.net/problem/13325
 */
public class BOJ_13325 {

    private static int k;
    private static int result;  //가중치의 총합
    private static int[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        int treeSize = (int) Math.pow(2, k + 1) - 1;  //노드 개수
        edges = new int[treeSize + 1];  //에지의 가중치

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= treeSize; i++) {
            edges[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, 0);
        System.out.println(result);
    }

    private static int dfs(int node, int height) {
        if (height == k) {  //리프 노드일 경우
            result += edges[node];
            return edges[node];
        }

        int left = dfs(2 * node, height + 1);  //왼쪽 자식의 가중치의 합
        int right = dfs(2 * node + 1, height + 1);  //오른쪽 자식 가중치의 합

        result += edges[node] + Math.abs(left - right);  //자식 노드의 가중치의 합의 차이를 더함
        return edges[node] + Math.max(left, right);
    }
}
