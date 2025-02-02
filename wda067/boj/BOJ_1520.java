package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 내리막 길 / 골드3
https://www.acmicpc.net/problem/1520
 */
public class BOJ_1520 {

    static int[] DR = {-1, 1, 0, 0};
    static int[] DC = {0, 0, -1, 1};
    static int[][] map;
    static Integer[][] dp;
    static int M, N, count = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());  //세로
        N = Integer.parseInt(st.nextToken());  //가로
        map = new int[M][N];
        dp = new Integer[M][N];  //dp[i][j]: (i, j)에서 도착지로 가는 경로의 개수

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int r, int c) {
        if (r == M - 1 && c == N - 1) {  //도착 지점에 도착하면 경로의 수 1 반환
            return 1;
        }

        if (dp[r][c] != null) {  //이미 방문한 지점은 저장된 경로의 수 반환
            return dp[r][c];
        }

        dp[r][c] = 0;  //현재 지점의 경로의 수를 0으로 초기화

        for (int dir = 0; dir < 4; dir++) {
            int nextR = r + DR[dir];
            int nextC = c + DC[dir];

            if (nextR < 0 || nextR >= M || nextC < 0 || nextC >= N) {
                continue;
            }

            if (map[nextR][nextC] < map[r][c]) {  //내리막 길일 경우
                dp[r][c] += dfs(nextR, nextC);
            }
        }

        return dp[r][c];  //탐색 완료 후 경로의 수 반환
    }
}
