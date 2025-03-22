import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 우주신과의 교감 / 골드3
https://www.acmicpc.net/problem/1774
 */
public class BOJ_1774 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] gods = new int[N + 1][2];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            gods[i][0] = X;
            gods[i][1] = Y;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.weight));
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double distance = getDistance(gods[i], gods[j]);
                pq.add(new Edge(i, j, distance));
            }
        }

        double totalWeight = 0;
        int edgeCount = 0;

        while (!pq.isEmpty() && edgeCount < N - 1) {  //MST의 최대 간선의 수는 N - 1개
            Edge cur = pq.poll();

            if (find(cur.from) != find(cur.to)) {
                union(cur.from, cur.to);
                totalWeight += cur.weight;
                edgeCount++;
            }
        }

        System.out.printf("%.2f\n", totalWeight);
    }

    private static double getDistance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) {
            parent[y] = x;
        } else if (x > y) {
            parent[x] = y;
        }
    }

    private static class Edge {

        int from, to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
