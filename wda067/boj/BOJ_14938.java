import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 서강그라운드 / 골드4
https://www.acmicpc.net/problem/14938
 */
public class BOJ_14938 {

    private static final int INF = 16;  //수색 가능한 최대 거리는 15

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);  //나머지는 INF (도달 불가)
            dist[i][i] = 0;  //자기 자신은 0
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = c;
            dist[b][a] = c;
        }

        //플로이드-워셜 -> 노드 수는 100, O(100^3)으로 해결 가능
        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    int newDist = dist[s][k] + dist[k][e];
                    if (dist[s][e] > newDist) {
                        dist[s][e] = newDist;
                    }
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] <= m) {  //i에서 j까지 도달 가능할 때
                    sum += items[j];
                }
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
