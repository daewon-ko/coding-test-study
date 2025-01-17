package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890 {
    static int n, l;
    static int[][] graph;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        graph = new int[n][n];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행 검사
        for (int i = 0; i < n; i++) {
            if (checkRow(i)) {
                count++;
            }
        }

        // 열 검사
        for (int i = 0; i < n; i++) {
            if (checkColumn(i)) {
                count++;
            }
        }

        System.out.println(count);
    }


    public static boolean checkRow(int y) {
        boolean[] visited = new boolean[n];

        for (int x = 0; x < n - 1; x++) {
            int currentHeight = graph[y][x];
            int nextHeight = graph[y][x + 1];


            if (currentHeight == nextHeight) continue;

            // 높이 차이가 2이상이면 false
            if (Math.abs(currentHeight - nextHeight) > 1) return false;


            if (currentHeight > nextHeight) {
                if (!canInstallRamp(y, x + 1, nextHeight, visited, true)) {
                    return false;  // 설치 불가하면 실패
                }
            } else if (currentHeight < nextHeight) {
                if (!canInstallRamp(y, x - l + 1, currentHeight, visited, true)) {
                    return false;  // 설치 불가하면 실패
                }
            }
        }

        return true;
    }


    public static boolean checkColumn(int x) {
        boolean[] visited = new boolean[n];

        for (int y = 0; y < n - 1; y++) {
            int currentHeight = graph[y][x];
            int nextHeight = graph[y + 1][x];


            if (currentHeight == nextHeight) continue;


            if (Math.abs(currentHeight - nextHeight) > 1) return false;

            // 내려가는 경사로
            if (currentHeight > nextHeight) {
                if (!canInstallRamp(x, y + 1, nextHeight, visited, false)) {
                    return false;
                }
            }

            // 올라가는 경사로
            else if (currentHeight < nextHeight) {
                if (!canInstallRamp(x, y - l + 1, currentHeight, visited, false)) {
                    return false;
                }
            }
        }

        return true;
    }


    public static boolean canInstallRamp(int fixed, int start, int height, boolean[] visited, boolean isRow) {
        for (int i = 0; i < l; i++) {
            int pos = start + i;

            // 경계 초과 체크
            if (pos < 0 || pos >= n) return false;

            // 높이 불일치 or 이미 경사로 설치된 칸
            // 행 검사 중인 경우
            if (isRow) {  // 행 검사
                if (graph[fixed][pos] != height || visited[pos]) {
                    return false;
                }
            } else {      // 열 검사
                if (graph[pos][fixed] != height || visited[pos]) {
                    return false;
                }
            }
        }

        // 경사로 설치 표시
        for (int i = 0; i < l; i++) {
            visited[start + i] = true;
        }

        return true;
    }
}




