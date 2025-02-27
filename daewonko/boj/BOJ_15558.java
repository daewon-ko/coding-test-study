package daewonko.boj;

// 백준

import java.util.*;

public class BOJ_15558 {
    static int n, k;
    static int[][] graph;
    static boolean[][] visited;

    public static void main(String[] args) {


            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            k = sc.nextInt();
            sc.nextLine();

            graph = new int[2][n];
            visited = new boolean[2][n];

            // 그래프 입력 받기
            for (int i = 0; i < 2; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < n; j++) {
                    graph[i][j] = line.charAt(j) - '0';
                }
            }

            sc.close();

            System.out.println(bfs() ? 1 : 0);
        }

        public static boolean bfs () {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0, 0, 0});
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x = cur[0];
                int line = cur[1];
                int time = cur[2];

                // 종료 조건: n 이상으로 이동하면 성공
                if (x >= n) {
                    return true;
                }


                if (x < time) {
                    continue;
                }


                int[][] moves = {{x + 1, line}, {x - 1, line}, {x + k, 1 - line}};

                for (int[] move : moves) {
                    int nx = move[0];
                    int ny = move[1];

                    if (nx >= n) {  // 성공 조건
                        return true;
                    }


                    if (nx > time && nx < n && graph[ny][nx] == 1 && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.add(new int[]{nx, ny, time + 1});
                    }
                }
            }
            return false;

        }
    }
