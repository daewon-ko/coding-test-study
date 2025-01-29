package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Item implements Comparable<Item> {
    int num;
    int count;

    public Item(int num, int count) {
        this.num = num;
        this.count = count;
    }

    @Override
    public int compareTo(Item o) {
        if (this.count == o.count) {
            // 개수 동일 -> 작은 숫자 먼저
            return Integer.compare(this.num, o.num);
        }
        return Integer.compare(this.count, o.count);
    }
}


public class BOJ_17140 {
    static int[][] map;
    static List<Item> arr;
    static boolean[] isExist;
    static int maxRow;
    static int maxCol;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        map = new int[100][100];
        maxRow = 3;
        maxCol = 3;

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (map[r][c] == k) {
            System.out.println(0);
            return;
        }

        for (int t = 1; t <= 100; t++) {
            if (maxRow >= maxCol) {
                sortRow();
            } else {
                sortCol();
            }

            if (map[r][c] == k) {
                System.out.println(t);
                return;
            }
        }
        System.out.println(-1);
    }

    static void sortRow() {
        int maxY = 0;
        for (int i = 0; i <= maxRow; i++) {
            arr = new ArrayList<>();
            isExist = new boolean[101];
            for (int j = 0; j <= maxCol; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                if (isExist[map[i][j]]) {
                    for (int k = 0; k <= maxCol; k++) {
                        if (arr.get(k).num == map[i][j]) {
                            arr.get(k).count++;
                            break;
                        }
                    }
                }
                else {
                    isExist[map[i][j]] = true;
                    arr.add(new Item(map[i][j], 1));
                }
            }
            Collections.sort(arr);
            for (int k = 0; k < arr.size(); k++) {
                map[i][k * 2] = arr.get(k).num;
                map[i][2 * k + 1] = arr.get(k).count;
            }

            maxY = Math.max(arr.size() * 2, Math.max(maxY, maxCol));
            if (maxY >= 100) {
                maxY = 100;
            }
            for (int k = arr.size() * 2; k <= maxY; k++) {
                map[i][k] = 0;
            }
        }
        maxCol = maxY;
    }

    static void sortCol() {
        int maxX = 0;
        for (int i = 0; i <= maxCol; i++) {
            arr = new ArrayList<>();
            isExist = new boolean[101];
            for (int j = 0; j <= maxRow; j++) {
                if (map[j][i] == 0) {
                    continue;
                }
                if (isExist[map[j][i]]) {
                    if (map[j][i] == 0) {
                        continue;
                    }
                    for (int k = 0; k <= maxRow; k++) {
                        if (arr.get(k).num == map[j][i]) {
                            arr.get(k).count += 1;
                            break;
                        }
                    }
                }
                else {
                    isExist[map[j][i]] = true;
                    arr.add(new Item(map[j][i], 1));
                }
            }
            Collections.sort(arr);
            for (int k = 0; k < arr.size(); k++) {
                map[k * 2][i] = arr.get(k).num;
                map[k * 2 + 1][i] = arr.get(k).count;
            }
            maxX = Math.max(arr.size() * 2, Math.max(maxX, maxRow));
            if (maxX >= 100) {
                maxX = 100;
            }
            for (int k = arr.size() * 2; k <= maxX; k++) {
                map[k][i] = 0;
            }
        }
        maxRow = maxX;
    }
}
