/*
프로그래머스 / 징검다리 건너기 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/64062
 */
public class PGS_64062 {

    private int[] stones;
    private int k;

    public int solution(int[] stones, int k) {
        this.stones = stones;
        this.k = k;
        int answer = 0;

        int s = 1;
        int e = 200_000_000;

        while (s <= e) {
            int m = (s + e) / 2;

            if (canCross(m)) {  //m명은 건널 수 있음 -> 다음 사람 시도
                answer = m;
                s = m + 1;
            } else {  //m명은 못 건넘 -> 인원 감소
                e = m - 1;
            }
        }

        return answer;
    }

    private boolean canCross(int m) {
        int skip = 0;

        for (int stone : stones) {
            if (stone - m < 0) {  //0 이하인 디딤돌일 때
                skip++;
                if (skip >= k) {  //그 개수가 k개 이상이면 false
                    return false;
                }
            } else {
                skip = 0;
            }
        }

        return true;
    }
}

