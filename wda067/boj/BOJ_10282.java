import static java.util.Comparator.comparingInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 해킹 / 골드4
https://www.acmicpc.net/problem/10282
 */
public class BOJ_10282 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());  //컴퓨터 개수
            int d = Integer.parseInt(st.nextToken());  //의존성 개수
            int c = Integer.parseInt(st.nextToken());  //해킹당한 컴퓨터 번호

            List<List<Node>> adjList = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adjList.add(new ArrayList<>());
            }

            int[] distance = new int[n + 1];  //최단 거리 배열
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[c] = 0;  //출발 노드

            boolean[] visited = new boolean[n + 1];

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                //b가 감염되면 s초 후 a도 감염
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                adjList.get(b).add(new Node(a, s));
            }

            //가중치를 기준으로 정렬
            PriorityQueue<Node> q = new PriorityQueue<>(comparingInt(Node::getWeight));
            q.add(new Node(c, 0));  //출발 노드 삽입

            while (!q.isEmpty()) {
                Node cur = q.poll();

                if (!visited[cur.to]) {  //방문하지 않은 노드일 경우
                    visited[cur.to] = true;

                    for (Node next : adjList.get(cur.to)) {  //인접 노드 탐색
                        int newDistance = distance[cur.to] + next.weight;
                        if (distance[next.to] > newDistance) {  //최단 거리 갱신
                            distance[next.to] = newDistance;
                            q.add(new Node(next.to, distance[next.to]));
                        }
                    }
                }
            }

            int count = 0;  //해킹당한 컴퓨터 개수
            int total = 0;  //제일 오래 걸린 시간
            for (int i = 1; i <= n; i++) {
                if (distance[i] != Integer.MAX_VALUE) {
                    count++;
                    total = Math.max(total, distance[i]);
                }
            }

            System.out.println(count + " " + total);
        }
    }

    private static class Node {

        private int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }
}
