package pkl0912.boj;

import java.io.*;
import java.util.*;


public class BOJ_12518 {
    static int n, k;
    static int minTime = Integer.MAX_VALUE;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(minTime);
        System.out.println(cnt);
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        int[] visited = new int[100001]; 
        Arrays.fill(visited, Integer.MAX_VALUE); 

        q.add(new int[]{n, 0});
        visited[n] = 0; 

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int cur = pos[0];
            int l = pos[1];

            if (cur == k) {
                if (minTime == Integer.MAX_VALUE) { 
                    minTime = l;
                    cnt = 1;
                } else if (minTime == l) { 
                    cnt++;
                }
                continue;
            }

            int[] next = {cur - 1, cur + 1, cur * 2};
            for (int nx : next) {
                if (nx >= 0 && nx <= 100000) {
                    // 더 빠른 시간에 방문한 적이 없거나, 같은 시간에 방문할 수 있는 경우
                    if (visited[nx] == Integer.MAX_VALUE || visited[nx] == l + 1) {
                        visited[nx] = l + 1;
                        q.add(new int[]{nx, l + 1});
                    }
                }
            }
        }
    }
}
