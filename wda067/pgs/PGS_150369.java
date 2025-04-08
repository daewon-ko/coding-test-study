/*
프로그래머스 / 택배 배달과 수거하기 / Level 2
https://school.programmers.co.kr/learn/courses/30/lessons/150369
 */
public class PGS_150369 {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliverRemain = 0;
        int pickupRemain = 0;

        for (int i = n - 1; i >= 0; i--) {
            deliverRemain += deliveries[i];
            pickupRemain += pickups[i];

            while (deliverRemain > 0 || pickupRemain > 0) {
                deliverRemain -= cap;
                pickupRemain -= cap;
                answer += (i + 1) * 2L;
            }
        }

        return answer;
    }
}
