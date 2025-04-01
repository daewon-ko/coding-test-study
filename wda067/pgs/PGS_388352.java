import java.util.ArrayList;
import java.util.List;

/*
프로그래머스 / 비밀 코드 해독 / Level 2
https://school.programmers.co.kr/learn/courses/30/lessons/388352
 */
class PGS_388352 {

    private List<int[]> candidates = new ArrayList<>();
    private int[][] q;
    private int[] ans;
    private int m, n;

    public int solution(int n, int[][] q, int[] ans) {
        this.q = q;
        this.ans = ans;
        this.m = ans.length;
        this.n = n;

        //1 ~ n에서 5개의 숫자를 선택하는 모든 경우의 수
        selectNumber(new int[5], 0, 1);

        int count = 0;
        for (int[] candidate : candidates) {
            if (isValid(candidate)) {
                count++;
            }
        }

        return count;
    }

    //모든 조합 생성
    private void selectNumber(int[] temp, int depth, int start) {
        if (depth == 5) {
            candidates.add(temp.clone());
            return;
        }

        for (int i = start; i <= n; i++) {
            temp[depth] = i;
            selectNumber(temp, depth + 1, i + 1);
        }
    }

    //현재 뽑힌 배열의 조건 만족 여부 판단
    private boolean isValid(int[] candidate) {
        for (int i = 0; i < m; i++) {
            if (countMatches(candidate, q[i]) != ans[i]) {
                return false;
            }
        }

        return true;
    }

    //두 배열의 일치하는 수의 개수
    private int countMatches(int[] candidate, int[] arr) {
        int count = 0;
        int i = 0;
        int j = 0;

        while (i < 5 && j < 5) {
            if (candidate[i] == arr[j]) {
                count++;
                i++;
                j++;
            } else if (candidate[i] < arr[j]) {
                i++;
            } else {
                j++;
            }
        }

        return count;
    }
}