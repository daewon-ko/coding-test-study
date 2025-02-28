package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 문자열 지옥에 빠진 호석
public class BOJ_20166 {
    static int n, m, k;
    static char [][] graph;
    static int[] dy = {0, 1, 0, -1, -1, 1, 1, -1};
    static int[] dx = {1, 0, -1, 0, 1, 1, -1, -1};
    static Set<String> targetWords = new HashSet<>();
    static Map<String, Integer> wordCount = new HashMap<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new char [n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < k; i++) {
            String word = br.readLine();
            targetWords.add(word);
            wordCount.put(word, 0);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, String.valueOf(graph[i][j]));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String word : targetWords) {
            sb.append(wordCount.get(word)).append("\n");
        }
        System.out.println(sb);

    }

    public static void dfs(int y, int x, String current) {

        if (targetWords.contains(current)) {
            wordCount.put(current, wordCount.get(current) + 1);
        }

        if (current.length() == 5) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            StringBuilder sb = new StringBuilder();
            int newY = (y + dy[i] + n) % n;
            int newX = (x + dx[i] + m) % m;

            sb.append(current).append(graph[newY][newX]);
            dfs(newY, newX,sb.toString());


        }
    }
}
