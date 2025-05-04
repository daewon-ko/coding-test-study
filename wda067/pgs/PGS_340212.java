/*
프로그래머스 / 퍼즐 게임 챌린지 / Level 2
https://school.programmers.co.kr/learn/courses/30/lessons/340212
 */
class PGS_340212 {

    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length;

        int s = 1;
        int e = 100_000;
        int answer = 0;

        while (s <= e) {
            int m = (s + e) / 2;
            long total = 0;

            for (int i = 0; i < n; i++) {
                int cur = diffs[i];
                int time = times[i];

                if (cur <= m) {
                    total += time;
                } else {
                    int diff = cur - m;
                    total += (time + times[i - 1]) * diff + time;
                }
            }

            if (limit >= total) {
                e = m - 1;
                answer = m;
            } else {
                s = m + 1;
            }
        }

        return answer;
    }
}
