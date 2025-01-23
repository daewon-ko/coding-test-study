package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> queue = new PriorityQueue<>((n1, n2) -> -n1[0] + n2[0]);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] now = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            queue.add(now);
        }

        long result = 0L;
        boolean[] isConfirmed = new boolean[10001];
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = now[1]; i > 0; i--) {  // d일 내에만 강연하면 됨
                if (!isConfirmed[i]) {
                    result += now[0];
                    isConfirmed[i] = true;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}
