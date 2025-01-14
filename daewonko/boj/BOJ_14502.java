package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

// 백준 연구소
public class BOJ_14502 {
    static int n, m;
    static int[][] graph;
    static boolean[][] visitedBlock;
    static boolean[][] visitedVirus;
    static int MAX = 0;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        wall(0);

        System.out.println(MAX);

    }

    public static void wall(int depth) {
        if (depth == 3) {
            spreadVirus();

            calculateSafeArea();

            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(graph[i][j] == 0) {
                    graph[i][j] = 1;
                    wall(depth + 1);
                    graph[i][j] = 0;
                }
            }
        }
    }

    private static void spreadVirus() {
        visitedVirus = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(graph[i][j] ==2 && !visitedVirus[i][j]) {
                    dfs(i, j);
                }
            }
        }
    }

    private static void calculateSafeArea() {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0 && !visitedVirus[i][j]) {
                    cnt++;
                }
            }
        }

        MAX = Math.max(MAX, cnt);
    }


    public static void dfs(int y, int x) {
        visitedVirus[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int newY = y + dy[i];
            int newX = x + dx[i];

            if (inRange(newY, newX) && graph[newY][newX] == 0 && !visitedVirus[newY][newX]) {
                dfs(newY, newX);
            }
        }

    }

    public static boolean inRange(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}
