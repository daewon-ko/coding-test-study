import java.util.Arrays;

/*
프로그래머스 / 입국심사 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/43238
 */
class PGS_43238 {

    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left = 1; // 최소 시간
        long right = (long) times[times.length - 1] * n; // 최대 시간
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long total = 0;

            for (int time : times) {
                total += mid / time; // 각 심사관별 mid 시간 내에 처리 가능한 인원 수
            }

            if (total >= n) {
                answer = mid; // 현재 mid 시간이 가능한 최소 시간일 수 있으므로 저장
                right = mid - 1; // 더 작은 시간 탐색
            } else {
                left = mid + 1; // 더 큰 시간 탐색
            }
        }

        return answer;
    }
}




