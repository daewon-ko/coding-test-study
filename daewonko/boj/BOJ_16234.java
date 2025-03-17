package daewonko.boj;
import java.io.*;
import java.util.*;
public class BOJ_16234 {


        static int N, L, R;
        static int[][] graph;
        static boolean[][] visited;
        static List<int[]> union; // 연합 국가들의 좌표 저장
        static int[] dx = {0, 1, 0, -1};
        static int[] dy = {-1, 0, 1, 0};

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            graph = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int steps = 0;

            while (true) {
                visited = new boolean[N][N];
                boolean moved = false;

                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < N; x++) {
                        if (!visited[y][x]) {
                            union = new ArrayList<>();
                            int sumPopulation = dfs(y, x);

                            if (union.size() > 1) {
                                moved = true;
                                int average = sumPopulation / union.size();
                                for (int[] pos : union) {
                                    graph[pos[0]][pos[1]] = average;
                                }
                            }
                        }
                    }
                }

                if (!moved) break; // 더 이상 인구 이동이 발생하지 않으면 종료
                steps++;
            }

            System.out.println(steps);
        }

        static int dfs(int y, int x) {
            visited[y][x] = true;
            union.add(new int[] {y, x});
            int sumPopulation = graph[y][x];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (inRange(ny, nx) && !visited[ny][nx]) {
                    int diff = Math.abs(graph[y][x] - graph[ny][nx]);
                    if (L <= diff && diff <= R) {
                        sumPopulation += dfs(ny, nx); // 연속적으로 탐색
                    }
                }
            }

            return sumPopulation;
        }

        static boolean inRange(int y, int x) {
            return y >= 0 && y < N && x >= 0 && x < N;
        }
    }


