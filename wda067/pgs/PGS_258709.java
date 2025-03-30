import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
프로그래머스 / 주사위 고르기 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/258709
 */
class PGS_258709 {
    private int n;
    private int[][] dice;
    private int[] bestCombination;
    private int maxWins;

    public int[] solution(int[][] dice) {
        this.n = dice.length;
        this.dice = dice;
        this.bestCombination = new int[n / 2];
        this.maxWins = -1;

        boolean[] selected = new boolean[n];
        selectDiceCombinations(0, 0, selected);

        // 주사위 번호를 1부터 시작하도록 변환
        for (int i = 0; i < bestCombination.length; i++) {
            bestCombination[i]++;
        }

        return bestCombination;
    }

    // 주사위 조합 선택
    private void selectDiceCombinations(int index, int count, boolean[] selected) {
        if (count == n / 2) {
            evaluateCombination(selected);
            return;
        }
        if (index >= n) return;

        selected[index] = true;
        selectDiceCombinations(index + 1, count + 1, selected);
        selected[index] = false;
        selectDiceCombinations(index + 1, count, selected);
    }

    // 선택된 조합 평가
    private void evaluateCombination(boolean[] selected) {
        List<Integer> aDice = new ArrayList<>();
        List<Integer> bDice = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (selected[i]) {
                aDice.add(i);
            } else {
                bDice.add(i);
            }
        }

        List<Integer> aSums = calculateAllSums(aDice);
        List<Integer> bSums = calculateAllSums(bDice);

        Collections.sort(bSums);

        int wins = 0;
        for (int aSum : aSums) {
            int winCount = findWinningCount(aSum, bSums);
            wins += winCount;
        }

        if (wins > maxWins) {
            maxWins = wins;
            for (int i = 0; i < aDice.size(); i++) {
                bestCombination[i] = aDice.get(i);
            }
        }
    }

    // 모든 가능한 주사위 눈의 합 계산
    private List<Integer> calculateAllSums(List<Integer> diceIndices) {
        List<Integer> sums = new ArrayList<>();
        calculateSumsRecursive(diceIndices, 0, 0, sums);
        return sums;
    }

    private void calculateSumsRecursive(List<Integer> diceIndices, int depth, int currentSum, List<Integer> sums) {
        if (depth == diceIndices.size()) {
            sums.add(currentSum);
            return;
        }

        int dieIndex = diceIndices.get(depth);
        for (int face : dice[dieIndex]) {
            calculateSumsRecursive(diceIndices, depth + 1, currentSum + face, sums);
        }
    }

    // A가 B를 이길 수 있는 경우의 수 계산 (이진 탐색 활용)
    private int findWinningCount(int aSum, List<Integer> bSums) {
        int left = 0;
        int right = bSums.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (bSums.get(mid) < aSum) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}


