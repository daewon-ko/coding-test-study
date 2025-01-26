package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11437 {
    static int N;
    static List<Integer>[] tree;
    static int[] floors;
    static int[] parents;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        tree = new List[N + 1];
        floors = new int[N + 1];
        parents = new int[N + 1];
        isVisited = new boolean[N + 1];

        // tree 초기화
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        // floor 계산
        bfs();

        // 공통 조상 노드 찾기
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int floor = Math.min(floors[node1], floors[node2]);

            int parent1 = getFloorParent(node1, floor);
            int parent2 = getFloorParent(node2, floor);

            while (parent1 != parent2) {
                parent1 = parents[parent1];
                parent2 = parents[parent2];
            }
            sb.append(parent1 + "\n");
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 1});  // {node, floor}
        floors[1] = 1;
        isVisited[1] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int floor = now[1] + 1;

            for (int next : tree[now[0]]) {
                if (!isVisited[next]) {
                    queue.add(new int[]{next, floor});
                    isVisited[next] = true;
                    floors[next] = floor;
                    parents[next] = now[0];
                }
            }
        }
    }

    static int getFloorParent(int node, int goal) {
        int floor = floors[node];
        while (floor != goal) {
            node = parents[node];
            floor--;
        }
        return node;
    }
}