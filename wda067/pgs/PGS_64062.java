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
        int count = 0;

        int s = 1;
        int e = 200_000_000;

        while (s <= e) {
            int m = (s + e) / 2;

            if (canCross(m)) {
                count = m;
                s = m + 1;
            } else {
                e = m - 1;
            }
        }

        return count;
    }

    private boolean canCross(int m) {
        int skip = 0;

        for (int stone : stones) {
            if (stone - m < 0) {
                skip++;
                if (skip >= k) {
                    return false;
                }
            } else {
                skip = 0;
            }
        }

        return true;
    }
}

