package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1939 {
    static class Move {
        int v;
        int weight;

        Move(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    static int N;
    static List<Move>[] ways;
    static int[] dp;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        ways = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            ways[i] = new ArrayList<>();
        }

        int max = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            ways[a].add(new Move(b, weight));
            ways[b].add(new Move(a, weight));

            max = Math.max(max, weight);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = max;

        while (left <= right) {
            int mid = (left + right) / 2;
            isVisited = new boolean[N + 1];
            if (bfs(start, end, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(right);
    }

    static boolean bfs(int start, int end, int mid) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;

        while (!q.isEmpty()) {
            start = q.poll();
            if (start == end) {
                return true;
            }
            for (Move now : ways[start]) {
                if (!isVisited[now.v] && mid <= now.weight) {
                    isVisited[now.v] = true;
                    q.add(now.v);
                }
            }
        }
        return false;
    }
}
