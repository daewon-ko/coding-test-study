package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
    static final int[] dx = new int[]{-1, 1, 0, 0};
    static final int[] dy = new int[]{0, 0, -1, 1};

    static int width;
    static int length;
    static String[][] map;
    static Queue<int[]> waterQ = new LinkedList<>();
    static int time = -1;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        map = new String[length][width];
        isVisited = new boolean[length][width];
        int[] Sloc = new int[2];
        for (int i = 0; i < length; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < width; j++) {
                map[i][j] = line[j];
                if (map[i][j].equals("*")) {
                    waterQ.add(new int[]{i, j});
                } else if (map[i][j].equals("S")) {  //고슴도치
                    Sloc = new int[]{i, j};
                    map[i][j] = ".";
                }
            }
        }

        boolean result = bfs(Sloc);
        if (result) {
            System.out.println(time);
        } else {
            System.out.println("KAKTUS");
        }
    }

    static boolean bfs(int[] Sloc) {
        Queue<int[]> q = new LinkedList<>();
        q.add(Sloc);
        isVisited[Sloc[0]][Sloc[1]] = true;

        while (!q.isEmpty()) {
            time++;
            int size = waterQ.size();
            for (int j = 0; j < size; j++) {
                int[] water = waterQ.poll();
                for (int i = 0; i < 4; i++) {
                    int x = water[0] + dx[i];
                    int y = water[1] + dy[i];
                    if (isValid(x, y) && map[x][y].equals(".")) {
                        waterQ.add(new int[]{x, y});
                        map[x][y] = "*";
                    }
                }
            }

            size = q.size();
            for (int j = 0; j < size; j++) {
                int[] goseum = q.poll();
                if (map[goseum[0]][goseum[1]].equals("D")) {
                    return true;
                }
                for (int i = 0; i < 4; i++) {
                    int x = goseum[0] + dx[i];
                    int y = goseum[1] + dy[i];
                    if (isValid(x, y) && !isVisited[x][y] && (map[x][y].equals(".") || map[x][y].equals("D"))) {
                        q.add(new int[]{x, y});
                        isVisited[x][y] = true;
                    }
                }
            }
        }
        return false;
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < length && y >= 0 && y < width;
    }
}

