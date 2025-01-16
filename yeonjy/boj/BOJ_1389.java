package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1389 {
    static int N;
    static int M;
    static List<Integer>[] friends;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        friends = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            friends[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }

        int[] total = new int[N + 1];
        int minTotal = Integer.MAX_VALUE;
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            total[i] = bfs(i);
            if (minTotal > total[i]) {
                minTotal = total[i];
                answer = i;
            }
        }
        System.out.println(answer);
    }

    static int bfs(int check) {
        int[] levels = new int[N + 1];
        int sum = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{check, 0});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int person = now[0];
            int depth = now[1];

            for (int friend : friends[person]) {
                if (levels[friend] == 0 && friend != check) {
                    levels[friend] = depth + 1;
                    sum += depth + 1;
                    queue.add(new int[]{friend, depth + 1});
                }
            }
        }
        return sum;
    }
}
