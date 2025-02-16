package daewonko.boj;

import java.util.*;
public class BOJ_3055 {


        static int R, C;
        static char[][] map;
        static int[][] dist;
        static Queue<int[]> water = new LinkedList<>(), hedgehog = new LinkedList<>();
        static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            R = sc.nextInt(); C = sc.nextInt();
            map = new char[R][C];
            dist = new int[R][C];

            int startX = 0, startY = 0;
            for (int i = 0; i < R; i++) {
                String line = sc.next();
                for (int j = 0; j < C; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'S') {
                        hedgehog.add(new int[]{i, j});
                        dist[i][j] = 1;
                    } else if (map[i][j] == '*') {
                        water.add(new int[]{i, j});
                    }
                }
            }

            System.out.println(bfs());
        }

        static String bfs() {
            while (!hedgehog.isEmpty()) {
                int wSize = water.size();
                for (int i = 0; i < wSize; i++) {
                    int[] w = water.poll();
                    for (int d = 0; d < 4; d++) {
                        int nx = w[0] + dx[d], ny = w[1] + dy[d];
                        if (nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] == '.') {
                            map[nx][ny] = '*';
                            water.add(new int[]{nx, ny});
                        }
                    }
                }

                int hSize = hedgehog.size();
                for (int i = 0; i < hSize; i++) {
                    int[] h = hedgehog.poll();
                    for (int d = 0; d < 4; d++) {
                        int nx = h[0] + dx[d], ny = h[1] + dy[d];
                        if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                            if (map[nx][ny] == 'D') return String.valueOf(dist[h[0]][h[1]]);
                            if (map[nx][ny] == '.') {
                                map[nx][ny] = 'S';
                                dist[nx][ny] = dist[h[0]][h[1]] + 1;
                                hedgehog.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
            return "KAKTUS";
        }
    }
