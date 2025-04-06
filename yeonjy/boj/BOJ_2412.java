package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2412 {
    static List<Integer>[] locations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        locations = new ArrayList[T + 1];
        for (int i = 0; i <= T; i++) {
            locations[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            locations[y].add(x);
        }

        System.out.println(bfs(T));
    }

    static int bfs(int T) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int depth = now[2];

            if (y == T) {
                return depth;
            }

            for (int ny = y - 2; ny <= y + 2; ny++) {
                if (ny < 0 || ny > T) {
                    continue;
                }
                for (int i = 0; i < locations[ny].size(); i++) {
                    int nx = locations[ny].get(i);
                    if (Math.abs(x - nx) <= 2) {
                        locations[ny].remove(i);
                        q.add(new int[]{nx, ny, depth + 1});
                        i--;
                    }
                }
            }
        }
        return -1;
    }
}
