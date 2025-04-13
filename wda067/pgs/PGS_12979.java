/*
프로그래머스 / 기지국 설치 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/12979
 */
class PGS_12979 {

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int coverage = 2 * w + 1;
        int start = 1;

        for (int station : stations) {
            int left = station - w;
            int right = station + w;

            if (start < left) {
                int gap = left - start;
                answer += (int) Math.ceil((double) gap / coverage);
            }

            start = right + 1;
        }

        if (start <= n) {
            int gap = n - start + 1;
            answer += (int) Math.ceil((double) gap / coverage);
        }

        return answer;
    }
}


