/*
프로그래머스 / 양궁대회 / Level 2
https://school.programmers.co.kr/learn/courses/30/lessons/92342
 */
public class PGS_92342 {

    private static int[] answer = {-1};
    private static int maxDiff = 0;

    public int[] solution(int n, int[] info) {
        dfs(n, 0, new int[11], info);
        return answer;
    }

    private void dfs(int arrowsLeft, int idx, int[] lion, int[] apeach) {
        if (idx == 11) {
            if (arrowsLeft > 0) lion[10] += arrowsLeft;

            int lionScore = 0, apeachScore = 0;
            for (int i = 0; i < 11; i++) {
                if (lion[i] == 0 && apeach[i] == 0) continue;

                if (lion[i] > apeach[i]) lionScore += (10 - i);
                else apeachScore += (10 - i);
            }

            if (lionScore > apeachScore) {
                int diff = lionScore - apeachScore;
                if (diff > maxDiff) {
                    maxDiff = diff;
                    answer = lion.clone();
                } else if (diff == maxDiff) {
                    for (int i = 10; i >= 0; i--) {
                        if (lion[i] > answer[i]) {
                            answer = lion.clone();
                            break;
                        } else if (lion[i] < answer[i]) {
                            break;
                        }
                    }
                }
            }

            if (arrowsLeft > 0) lion[10] -= arrowsLeft;
            return;
        }

        if (arrowsLeft > apeach[idx]) {
            lion[idx] = apeach[idx] + 1;
            dfs(arrowsLeft - lion[idx], idx + 1, lion, apeach);
            lion[idx] = 0;
        }

        dfs(arrowsLeft, idx + 1, lion, apeach);
    }
}

