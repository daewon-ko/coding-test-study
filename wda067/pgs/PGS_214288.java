import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/*
프로그래머스 / 상담원 인원 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/214288
 */
class PGS_214288 {

    private static int k;
    private static int min = Integer.MAX_VALUE;
    private static List<List<int[]>> requests = new LinkedList<>();  //유형별 요청

    public int solution(int k, int n, int[][] reqs) {
        this.k = k;  //상담 유형의 수

        for (int i = 0; i <= k; i++) {
            requests.add(new LinkedList<>());
        }

        for (int[] req : reqs) {  //요청별 유형 분류
            requests.get(req[2]).add(new int[]{req[0], req[1]});
        }

        //멘토는 최소 1명씩 배정되어야 하므로 남은 멘토는 (n - k)명
        allocateMentors(1, n - k, new int[k + 1]);
        return min;
    }

    private void allocateMentors(int type, int remaining, int[] mentors) {
        if (type > k) {  //배정 완료
            min = Math.min(min, calculateWaiting(mentors));
            return;
        }

        for (int i = 0; i <= remaining; i++) {  //남은 멘토 추가 배정
            mentors[type] = i + 1;  //최소 1명 배정
            allocateMentors(type + 1, remaining - i, mentors);  //배정한 만큼 제외하고 다음 유형으로 넘어감
        }
    }

    private int calculateWaiting(int[] mentors) {
        int waiting = 0;  //대기 시간

        for (int i = 1; i <= k; i++) {  //모든 유형에 대하여 반복
            int mentor = mentors[i];  //i유형의 멘토 수
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int[] req : requests.get(i)) {  //i유형의 요청 수
                int start = req[0];  //상담 시작 시각
                int duration = req[1];  //상담 시간

                while (!pq.isEmpty() && pq.peek() <= start) {  //상담이 끝난 멘토 제외
                    pq.poll();
                }

                if (pq.size() < mentor) {  //상담 가능한 멘토가 존재할 때
                    pq.add(start + duration);
                } else {
                    int next = pq.poll();  //다음 상담 가능한 시각
                    waiting += (next - start);  //대기 시간 누적
                    pq.add(next + duration);
                }
            }
        }

        return waiting;
    }
}

