package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_17144 {
    static int[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static List<int[]> air;
    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        air = new ArrayList<>();
        graph = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == -1) air.add(new int[]{i, j});
            }
        }

        while (t-- > 0) {
            spread();
            fresh();
        }

        System.out.println(getTotalDust());
    }

    public static void spread() {
        int[][] temp = new int[r][c];
        for (int i = 0; i < r; i++) {
            System.arraycopy(graph[i], 0, temp[i], 0, c);
        }

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (graph[x][y] > 0) {
                    int spreadAmount = graph[x][y] / 5;
                    int spreadCount = 0;
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (nx >= 0 && nx < r && ny >= 0 && ny < c && graph[nx][ny] != -1) {
                            temp[nx][ny] += spreadAmount;
                            spreadCount++;
                        }
                    }
                    temp[x][y] -= spreadAmount * spreadCount;
                }
            }
        }
        graph = temp;
    }

    public static void fresh() {
        int top = air.get(0)[0];
        int down = air.get(1)[0];

        // 위쪽 공기청정기 순환
        for (int i = top - 1; i > 0; i--) graph[i][0] = graph[i - 1][0];
        for (int i = 0; i < c - 1; i++) graph[0][i] = graph[0][i + 1];
        for (int i = 0; i < top; i++) graph[i][c - 1] = graph[i + 1][c - 1];
        for (int i = c - 1; i > 1; i--) graph[top][i] = graph[top][i - 1];
        graph[top][1] = 0;

        // 아래쪽 공기청정기 순환
        for (int i = down + 1; i < r - 1; i++) graph[i][0] = graph[i + 1][0];
        for (int i = 0; i < c - 1; i++) graph[r - 1][i] = graph[r - 1][i + 1];
        for (int i = r - 1; i > down; i--) graph[i][c - 1] = graph[i - 1][c - 1];
        for (int i = c - 1; i > 1; i--) graph[down][i] = graph[down][i - 1];
        graph[down][1] = 0;
    }

    public static int getTotalDust() {
        int total = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (graph[i][j] > 0) total += graph[i][j];
            }
        }
        return total;
    }
}
 