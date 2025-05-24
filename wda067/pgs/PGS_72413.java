import java.util.Arrays;

/*
프로그래머스 / 합승 택시 요금 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/72413
 */
class PGS_72413 {

    int INF = 200 * 100_000;  //간선의 수 (200 - 1) * 최대 요금 (100,000)

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] adjMatrix = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(adjMatrix[i], INF);
            adjMatrix[i][i] = 0;
        }

        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int d = fare[2];

            adjMatrix[u][v] = d;
            adjMatrix[v][u] = d;
        }

        //플로이드 워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int newDist = adjMatrix[i][k] + adjMatrix[k][j];
                    if (adjMatrix[i][j] > newDist) {
                        adjMatrix[i][j] = newDist;
                    }
                }
            }
        }

        int answer = INF;
        for (int k = 1; k <= n; k++) {
            //(s -> k) + (k -> a) + (k -> b)의 최댓값
            int total = adjMatrix[s][k] + adjMatrix[k][a] + adjMatrix[k][b];
            answer = Math.min(answer, total);
        }

        return answer;
    }
}


