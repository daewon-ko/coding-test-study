package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16235 {
    static final int[] dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

    static int N;
    static PriorityQueue<Integer>[][] treeMap;
    static int[][] nutritionMap;
    static int[][] addNutritions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        treeMap = new PriorityQueue[N][N];
        nutritionMap = new int[N][N];
        addNutritions = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                nutritionMap[i][j] = 5;
                addNutritions[i][j] = Integer.parseInt(st.nextToken());
                treeMap[i][j] = new PriorityQueue<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            treeMap[x][y].add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            springAndSummer();
            fall();
            winter();
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += treeMap[i][j].size();
            }
        }
        System.out.println(result);
    }

    static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int diedNutri = 0;
                int nutrition = nutritionMap[i][j];
                List<Integer> ages = new ArrayList<>();
                while (!treeMap[i][j].isEmpty()) {
                    int age = treeMap[i][j].poll();
                    if (nutrition < age) {
                        diedNutri += age / 2;
                    } else {
                        nutrition -= age;
                        ages.add(age + 1);
                    }
                }
                treeMap[i][j].addAll(ages);
                nutritionMap[i][j] = nutrition + diedNutri;
            }
        }
    }

    static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int tree : treeMap[i][j]) {
                    if (tree % 5 == 0) {
                        for (int k = 0; k < 8; k++) {
                            int x = i + dx[k];
                            int y = j + dy[k];
                            if (x >= 0 && y >= 0 && x < N && y < N) {
                                treeMap[x][y].add(1);
                            }
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutritionMap[i][j] += addNutritions[i][j];
            }
        }
    }
}
