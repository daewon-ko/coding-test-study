papckage yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] result = bfs(N, K);
        System.out.println(result[0] + "\n" + result[1]);
    }

    static int[] bfs(int N, int K) {
        int[] times = new int[300_001];
        times[N] = 0;
        int min = Integer.MAX_VALUE;
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == K) {
                min = times[now];
                count++;
            }
            if (times[now] > min) {
                break;
            }

            int next;
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    next = now + 1;
                } else if (i == 1) {
                    next = now - 1;
                } else {
                    next = now * 2;
                }

                if ((next >= 0 && next <= 300000) && (times[next] == times[now] + 1 || times[next] == 0)) {
                    times[next] = times[now] + 1;
                    queue.add(next);
                }
            }
        }
        return new int[]{min, count};
    }
}
