package boj;

import static java.util.Comparator.comparingInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 나무 재테크 / 골드3
https://www.acmicpc.net/problem/16235
 */
public class BOJ_16235 {

    static int[] DR = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] DC = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int N, M, K;
    static int[][] nutrients;
    static int[][] addedNutrients;
    static PriorityQueue<Tree> trees = new PriorityQueue<>(comparingInt(o -> o.age));
    static List<Tree> deadTrees = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nutrients = new int[N + 1][N + 1];
        addedNutrients = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(nutrients[i], 5);  //초기 양분
            for (int j = 1; j <= N; j++) {
                addedNutrients[i][j] = Integer.parseInt(st.nextToken());  //추가되는 양분
            }
        }

        for (int i = 0; i < M; i++) {  //나무 정보
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());  //나무의 나이

            trees.add(new Tree(x, y, z));
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(trees.size());
    }

    //어린 나무부터 양분을 먹고, 먹지 못한 나무는 죽는다.
    static void spring() {
        PriorityQueue<Tree> newTrees = new PriorityQueue<>(comparingInt(o -> o.age));

        while (!trees.isEmpty()) {
            Tree tree = trees.poll();
            if (nutrients[tree.r][tree.c] >= tree.age) {  //영양분이 충분할 경우
                nutrients[tree.r][tree.c] -= tree.age;
                tree.age++;
                newTrees.add(tree);
            } else {
                deadTrees.add(tree);
            }
        }

        trees = newTrees;
    }

    //죽은 나무 -> 양분
    static void summer() {
        for (Tree deadTree : deadTrees) {
            nutrients[deadTree.r][deadTree.c] += deadTree.age / 2;
        }
        deadTrees.clear();
    }

    //나무가 번식한다.
    static void fall() {
        List<Tree> newTrees = new ArrayList<>();

        for (Tree tree : trees) {
            if (tree.age % 5 == 0) {  //나이가 5의 배수일 때
                for (int i = 0; i < 8; i++) {
                    int nextR = tree.r + DR[i];
                    int nextC = tree.c + DC[i];

                    if (nextR > 0 && nextR <= N && nextC > 0 && nextC <= M) {
                        newTrees.add(new Tree(nextR, nextC, tree.age));
                    }
                }
            }
        }

        trees.addAll(newTrees);
    }

    //영양분 추가
    static void winter() {
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                nutrients[i][j] += addedNutrients[i][j];
            }
        }
    }

    static class Tree {
        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }
    }
}
