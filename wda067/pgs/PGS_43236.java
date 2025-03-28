import java.util.Arrays;

/*
프로그래머스 / 징검다리 / Level 4
https://school.programmers.co.kr/learn/courses/30/lessons/43236
 */
class PGS_43236 {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);  //돌 위치 정렬
        int left = 1, right = distance;  //가능한 최소/최대 거리

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canRemoveRocks(rocks, distance, n, mid)) {
                left = mid + 1;  //더 큰 거리도 가능할 수 있음
            } else {
                right = mid - 1;  //거리를 줄여야 함
            }
        }

        return right;
    }

    private boolean canRemoveRocks(int[] rocks, int distance, int n, int minDist) {
        int removed = 0, prev = 0;

        for (int rock : rocks) {
            if (rock - prev < minDist) {  //최소 거리보다 작으면 제거
                removed++;
                if (removed > n) {
                    return false;
                }
            } else {
                prev = rock;
            }
        }

        //마지막 거리 체크
        if (distance - prev < minDist) {
            removed++;
        }

        return removed <= n;
    }
}


