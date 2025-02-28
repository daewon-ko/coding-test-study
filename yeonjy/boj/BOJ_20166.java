package yeonjy.boj;

import java.util.*;
import java.io.*;

public class BOJ_20166 {
    static StringBuilder sb = new StringBuilder();
    static int N, M, K;
    static char[][] wordMatrix;
    static List<String> queries = new ArrayList<>();
    static Map<String, Integer> wordMap = new HashMap<>();

    static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};

    static void rec(int x, int y, int len, String word) {
        if(wordMap.containsKey(word)) {
            wordMap.put(word, wordMap.get(word) + 1);
        } else {
            wordMap.put(word, 1);
        }

        if(len == 5) return;

        for(int i = 0; i < 8; i++) {
            int dx = (x + DIR[i][0]) % N; 
            int dy = (y + DIR[i][1]) % M;

            if(dx < 0) dx += N;
            if(dy < 0) dy += M;

            rec(dx, dy, len + 1, word + wordMatrix[dx][dy]);
        }
    }

    static void pro() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                rec(i, j, 1, wordMatrix[i][j] + "");
            }
        }

        for(String query : queries) {
            sb.append(wordMap.getOrDefault(query, 0)).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        wordMatrix = new char[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] words = st.nextToken().toCharArray();
            for(int j = 0; j < M; j++) {
                wordMatrix[i][j] = words[j];
            }
        }

        for(int i = 1; i <= K; i++) {
            queries.add(br.readLine());
        }
        pro();
    }
}
