package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1774 {
    static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        double dist;
        public Edge(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }
    static int[] parents;
    static Node[] nodes;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new Node[N + 1];
        parents = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i + 1] = new Node(x, y);
            parents[i + 1] = i + 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            unionParent(a, b);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                double cost = Math.sqrt(Math.pow(nodes[i].x - nodes[j].x, 2) + Math.pow(nodes[i].y - nodes[j].y, 2));
                pq.add(new Edge(i, j, cost));
            }
        }
        double cost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (findParent(edge.a) != findParent(edge.b)) {
                unionParent(edge.a, edge.b);
                cost += edge.dist;
            }
        }
        System.out.printf("%.2f", cost);
    }

    static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static int findParent(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = findParent(parents[x]);
    }
}
