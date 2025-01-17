package pkl0912.boj;
import java.util.*;
import java.io.*;
public class BOJ_14890 {
    static int answer;
    static int n;
    static int l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n][n];
        int[][] graph2 = new int[n][n]; 

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i][j] = num;
                graph2[j][i] = num; 
            }
        }

        answer = 0;
        find(graph); 
        find(graph2); 
        System.out.println(answer);
    }

    public static void find(int[][] graph) {
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n]; 
            boolean flag = true; 

            for (int j = 0; j < n - 1; j++) {
                if (graph[i][j] == graph[i][j + 1]) {
                    continue;
                } else if (graph[i][j] + 1 == graph[i][j + 1]) {
                    // 오르막길 설치
                    if (!canPlaceSlope(graph[i], j, -1, visited)) {
                        flag = false;
                        break;
                    }
                } else if (graph[i][j] - 1 == graph[i][j + 1]) {
                    // 내리막길 설치
                    if (!canPlaceSlope(graph[i], j + 1, 1, visited)) {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer++;
            }
        }
    }

    private static boolean canPlaceSlope(int[] row, int start, int direction, boolean[] visited) {
        for (int k = 0; k < l; k++) {
            int idx = start + k * direction;
            if (idx < 0 || idx >= n || visited[idx] || row[start] != row[idx]) {
                return false; 
            }
            visited[idx] = true; 
        }
        return true;
    }
}
