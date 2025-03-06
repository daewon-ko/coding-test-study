package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_11559 {
    static final int[] dx = new int[]{-1, 1, 0, 0};
    static final int[] dy = new int[]{0, 0, -1, 1};

    static char[][] map;
    static boolean[][] isVisited;
    static int depth;
    static List<int[]> connectedBlocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int result = 0;

        boolean isLast = false;
        while (!isLast) {
            isLast = true;
            isVisited = new boolean[12][6];
            // 터지기
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (!isVisited[i][j] && map[i][j] != '.') {
                        depth = 0;
                        connectedBlocks = new ArrayList<>();
                        dfs(i, j);
                        if (connectedBlocks.size() >= 4) {
                            isLast = false;
                            for (int[] block : connectedBlocks) {
                                map[block[0]][block[1]] = '.';
                            }
                        }
                    }
                }
            }
            if (!isLast) {
                result++;
            } else {
                break;
            }
            // 중력
            for (int i = 0; i < 6; i++) {  // y
                List<Character> line = new ArrayList<>();
                for (int j = 11; j >= 0; j--) {  // x
                    if (map[j][i] != '.') {
                        line.add(map[j][i]);  // 첫 번째가 제일 밑 값
                    }
                }

                for (int j = 0; j < line.size(); j++) {
                    int mapIdx = 11 - j;
                    map[mapIdx][i] = line.get(j);
                }
                for (int j = 0; j < 12 - line.size(); j++) {
                    map[j][i] = '.';
                }
            }
            System.out.println("------Turn------");
            for (int i = 0; i < 12; i++) {
                System.out.println(Arrays.toString(map[i]));
            }
        }
        System.out.println(result);
    }

    static void dfs(int x, int y) {
        isVisited[x][y] = true;
        connectedBlocks.add(new int[]{x, y}); // 연결된 블록 좌표 저장
        char now = map[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isValid(nx, ny) && !isVisited[nx][ny] && now == map[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < 12 && y < 6;
    }
}
