package daewonko.boj;
import java.io.*;
import java.util.*;

public class BOJ_10282 {

        static class Node implements Comparable<Node> {
            int v, time;

            Node(int v, int time) {
                this.v = v;
                this.time = time;
            }

            public int compareTo(Node o) {
                return Integer.compare(this.time, o.time);
            }
        }

        static List<List<Node>> graph;
        static int[] dist;
        static int INF = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            int T = Integer.parseInt(br.readLine());

            while (T-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph = new ArrayList<>();
                for (int i = 0; i <= n; i++) {
                    graph.add(new ArrayList<>());
                }
                dist = new int[n + 1];
                Arrays.fill(dist, INF);

                for (int i = 0; i < d; i++) {
                    st = new StringTokenizer(br.readLine());
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int s = Integer.parseInt(st.nextToken());
                    graph.get(b).add(new Node(a, s));
                }

                dijkstra(c);

                int count = 0, maxTime = 0;
                for (int i = 1; i <= n; i++) {
                    if (dist[i] != INF) {
                        count++;
                        maxTime = Math.max(maxTime, dist[i]);
                    }
                }
                sb.append(count).append(" ").append(maxTime).append("\n");
            }
            System.out.print(sb);
        }

        static void dijkstra(int start) {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(start, 0));
            dist[start] = 0;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (cur.time > dist[cur.v]) continue;

                for (Node next : graph.get(cur.v)) {
                    int newTime = cur.time + next.time;
                    if (newTime < dist[next.v]) {
                        dist[next.v] = newTime;
                        pq.add(new Node(next.v, newTime));
                    }
                }
            }
        }
    }

