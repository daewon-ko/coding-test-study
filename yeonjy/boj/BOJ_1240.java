package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1240 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] tree = new int[N + 1][N + 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            tree[a][b] = distance;
            tree[b][a] = distance;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(bfs(tree, a, b)).append("\n");
        }
        System.out.println(sb);
    }

    static int bfs(int[][] tree, int st, int en) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{st, 0});
        boolean[] isVisited = new boolean[N + 1];
        isVisited[st] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int a = now[0];
            int distance = now[1];

            if (a == en) {
                return distance;
            }

            for (int i = 1; i <= N; i++) {
                if (!isVisited[i] && tree[a][i] > 0) {
                    isVisited[i] = true;
                    q.add(new int[]{i, distance + tree[a][i]});
                }
            }
        }
        return -1;
    }
}
