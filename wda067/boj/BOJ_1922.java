import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 네트워크 연결 / 골드4
https://www.acmicpc.net/problem/1922
 */
public class BOJ_1922 {

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //1,000
        int M = Integer.parseInt(br.readLine());  //100,000

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());  //10,000

            edges.add(new Edge(a, b, c));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        for (int i = 0; i < M; i++) {
            pq.add(edges.get(i));
        }

        int edgeCount = 0;
        int min = 0;  //최소 비용

        while (edgeCount < N - 1) {  //MST의 에지 개수는 노드 - 1개
            Edge cur = pq.poll();
            int from = cur.from;
            int to = cur.to;
            int weight = cur.weight;

            if (find(from) != find(to)) {  //같은 부모가 아니라면 연결해도 사이클이 생기지 않음
                union(from, to);
                min += weight;
                edgeCount++;
            }
        }

        System.out.println(min);
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parents[b] = a;
        }
    }

    private static class Edge {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
