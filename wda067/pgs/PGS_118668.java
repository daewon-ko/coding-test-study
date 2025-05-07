import java.util.Arrays;

/*
프로그래머스 / 코딩 테스트 공부 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/118668
 */
class PGS_118668 {

    public int solution(int alp, int cop, int[][] problems) {
        //목표 설정
        int maxAlp = 0, maxCop = 0;
        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }

        //처음부터 목표를 넘었을 경우
        if (alp > maxAlp) {
            alp = maxAlp;
        }
        if (cop > maxCop) {
            cop = maxCop;
        }

        int[][] dp = new int[maxAlp + 2][maxCop + 2];  //dp[i][j]: (i, j)에 도달하는 데 걸리는 최단시간
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                //공부만 해서 올리는 경우
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);  //알고력
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);  //코딩력

                //문제를 푸는 경우
                for (int[] p : problems) {
                    int reqAlp = p[0], reqCop = p[1];
                    int rwdAlp = p[2], rwdCop = p[3], cost = p[4];

                    if (i >= reqAlp && j >= reqCop) {  //풀 수 있는 문제일 때
                        int nextAlp = Math.min(maxAlp, i + rwdAlp);
                        int nextCop = Math.min(maxCop, j + rwdCop);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + cost);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}

