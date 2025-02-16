package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_16946 {
    static int N, M;
    static int[][] map;
    static int[][] group;
    static Map<Integer, Integer> groupSize = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        group = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int groupNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && group[i][j] == 0) {
                    bfs(i, j, groupNum++);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    sb.append(0);
                } else {
                    Set<Integer> nearGroups = new HashSet<>();
                    int sum = 1;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && group[nx][ny] > 0) {
                            nearGroups.add(group[nx][ny]);
                        }
                    }
                    for (int g : nearGroups) {
                        sum += groupSize.get(g);
                    }
                    sb.append(sum % 10);
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void bfs(int x, int y, int groupNum) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        group[x][y] = groupNum;
        int count = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            count++;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0 && group[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                    group[nx][ny] = groupNum;
                }
            }
        }

        groupSize.put(groupNum, count);
    }
}