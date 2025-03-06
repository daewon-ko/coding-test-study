package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_11559 {

    static boolean[][] visited;
    static char[][] graph;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new char[12][6];


        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        while (true) {
            if (!executePop()) {
                break;
            }
            // 연쇄적으로 터트림 성공
            cnt++;

            // 중력으로 인한 떨어트림 수행
            applyGravity();
        }

        System.out.println(cnt);

    }

    public static boolean executePop() {
        boolean isPop = false;
        visited = new boolean[12][6];
        List<int[]> toPop = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (!visited[i][j] && graph[i][j] != '.') {
                    List<int[]> cluster = bfs(i, j);

                    // 터트릴 좌표점이 4개 이상이면 flag가 true가 된다.
                    if (cluster.size() >= 4) {
                        toPop.addAll(cluster);
                        isPop = true;
                    }
                }

            }
        }

        // 모든 좌표점을 순회한 이후에
        for (int[] graphToPop : toPop) {
            int y = graphToPop[0];
            int x = graphToPop[1];

            // .로 초기화 한다.
            graph[y][x] = '.';

        }

        return isPop;


    }

    public static List<int[]> bfs(int y, int x) {
        List<int[]> cluster = new ArrayList<>();

        Queue<int[]> queue = new LinkedList<>();
        visited[y][x] = true;

        char color = graph[y][x];
        queue.add(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            cluster.add(poll);

            // y가 배열의 0번째 인덱스 좌표에 해당
            int pollY = poll[0];
            // x가 배열의 1번째 인덱스 좌표에 해당
            int pollX = poll[1];


            for (int i = 0; i < 4; i++) {
                int newY = dy[i] + pollY;
                int newX = dx[i] + pollX;

                if (inRange(newY, newX) && !visited[newY][newX] && graph[newY][newX] == color) {
                    visited[newY][newX] = true;
                    queue.add(new int[]{newY, newX});

                }

            }
        }

        return cluster;

    }

    public static void applyGravity() {
        Queue<Character> queue = new LinkedList<>();

        for (int i = 0; i < 6; i++) {
            for (int j = 11; j >= 0; j--) {
                if (graph[j][i] != '.') {
                    queue.add(graph[j][i]);
                }
            }

            for (int j = 11; j >= 0; j--) {
                if (!queue.isEmpty()) {
                    graph[j][i] = queue.poll();
                } else {
                    graph[j][i] = '.';
                }
            }

        }


    }

    public static boolean inRange(int y, int x) {
        return y >= 0 && y < 12 && x >= 0 && x < 6;
    }
}



