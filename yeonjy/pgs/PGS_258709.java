package yeonjy.pgs;

import java.util.*;

class PGS_258709 {
    static int N;
    static int[][] D;
    static int[] maxList;
    static int maxWin;
    static nt tmpSum;
    static int[] A;
    static int[] B;

    static List<Integer> sumList;

    public int[] solution(int[][] dice) {
        N = dice.length;
        D = dice;
        maxList = new int[N/2];
        maxWin = Integer.MIN_VALUE;
        A = new int[N/2];
        B = new int[N/2];
        
        select(0, 0);
        return maxList;
    }
    
    public void select(int cur, int ind) {
        if (ind == N/2) {
            int index = 0;
            for (int i = 0; i < N; i++) {
                boolean flag = false;
                for (int j = 0; j < N/2; j++)
                    if (A[j] == i) flag = true;
                if (flag) continue;
                B[index++] = i;
            }
            calCnt();
            return;
        }
        if (cur >= N) {
            return;
        }
        A[ind] = cur;
        select(cur+1, ind+1);
        select(cur+1, ind);
    }

    public void calSumA(int ind, int sum) {
        if (ind == N/2) {
            int lo = 0;
            int hi = sumList.size();
            while (lo + 1 < hi) {
                int mid = (lo + hi) / 2;
                if (sumList.get(mid) < sum) lo = mid;
                else hi = mid;
            }
            tmpSum += (lo + 1);
            return;
        }
        for (int i = 0; i < 6; i++)
            calSumA(ind+1, sum + D[A[ind]][i]);
    }

    public void calSumB(int ind, int sum) {
        if (ind == N/2) {
            sumList.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++)
            calSumB(ind+1, sum + D[B[ind]][i]);
    }

    public void calCnt() {
        sumList = new ArrayList<>();
        tmpSum = 0;

        calSumB(0, 0);
        Collections.sort(sumList);

        calSumA(0, 0);
        if (maxWin < tmpSum) {
            maxWin = tmpSum;
            for (int i = 0; i < N/2; i++)
                maxList[i] = A[i] + 1;
        }
    }
}
