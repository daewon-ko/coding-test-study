import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 숨바꼭질 2 / 골드4
https://www.acmicpc.net/problem/12851
 */
public class BOJ_12851 {

    private static final int N_SIZE = 100_001;
    private static int[] dist = new int[N_SIZE];
    private static int[] path = new int[N_SIZE];
    private static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  //0 ~ 100,000
        K = Integer.parseInt(st.nextToken());  //0 ~ 100,000

        System.out.println(bfs());
    }

    private static int bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        pq.add(new int[]{0, N});
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;
        path[N] = 1;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curTime = cur[0];
            int curPos = cur[1];

            if (curTime > dist[curPos]) {
                continue;
            }

            for (int next : new int[]{curPos - 1, curPos + 1, curPos * 2}) {
                if (next < 0 || next >= N_SIZE) {
                    continue;
                }

                if (dist[next] > curTime + 1) {
                    dist[next] = curTime + 1;
                    path[next] = path[curPos];
                    pq.add(new int[]{dist[next], next});
                } else if (dist[next] == curTime + 1) {
                    path[next] += path[curPos];
                }
            }
        }

        System.out.println(dist[K]);
        return path[K];
    }
}
