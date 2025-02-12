package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16946 {
    static final int[] dx = new int[]{1, -1, 0, 0};
    static final int[] dy = new int[]{0, 0, -1, 1};

    static int N;
    static int M;
    static int[][] map;
    static int[][] groupMap;
    static List<Integer> groupList = new ArrayList<>();
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        groupMap = new int[N][M];
        groupList.add(0, 0);
        map = new int[N][M];
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !isVisited[i][j]) {
                    makeGroupMap(i, j, num);
                    num++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    int cnt = countLinked(i, j);
                    sb.append(cnt);
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void makeGroupMap(int x, int y, int num) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        isVisited[x][y] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            count++;
            groupMap[now[0]][now[1]] = num;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !isVisited[nx][ny] && map[nx][ny] == 0) {
                    isVisited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        groupList.add(num, count);
    }

    static int countLinked(int x, int y) {
        int count = 1;
        Set<Integer> groups = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                groups.add(groupMap[nx][ny]);
            }
        }
        for (int i : groups) {
            count = (count + groupList.get(i)) % 10;
        }
        return count;
    }
}
