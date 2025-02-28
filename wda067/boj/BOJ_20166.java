import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
백준 / 문자열 지옥에 빠진 호석 / 골드4
https://www.acmicpc.net/problem/20166
 */
public class BOJ_20166 {

    private static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
    private static Map<String, Integer> hashMap = new HashMap<>();
    private static char[][] map;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //격자 초기화
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        //K개의 문자열
        String[] targets = new String[K];
        for (int i = 0; i < K; i++) {
            targets[i] = br.readLine();
        }

        for (String target : targets) {
            hashMap.put(target, 0);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(map[i][j]);
                dfs(new int[]{i, j}, sb);
            }
        }

        StringBuilder result = new StringBuilder();
        for (String target : targets) {
            result.append(hashMap.get(target)).append("\n");
        }

        System.out.print(result);
    }

    private static void dfs(int[] start, StringBuilder word) {
        String cur = word.toString();
        hashMap.put(cur, hashMap.getOrDefault(cur, 0) + 1);

        if (word.length() == 5) {
            return;
        }

        for (int dir = 0; dir < 8; dir++) {
            int nextR = start[0] + dr[dir];
            int nextC = start[1] + dc[dir];

            if (nextR < 0) {
                nextR = N - 1;
            }
            if (nextR >= N) {
                nextR = 0;
            }
            if (nextC < 0) {
                nextC = M - 1;
            }
            if (nextC >= M) {
                nextC = 0;
            }

            word.append(map[nextR][nextC]);
            dfs(new int[]{nextR, nextC}, word);
            word.deleteCharAt(word.length() - 1);  //백트래킹
        }
    }
}
