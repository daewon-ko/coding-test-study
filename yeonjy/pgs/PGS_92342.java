package yeonjy.pgs;

public class PGS_92342 {
    static int n;
    static int[] apeach;
    static int[] lion;
    static boolean finish;
    static int[] result;
    static int max = 0;
    
    public int[] solution(int n, int[] info) {
        this.n = n;
        this.apeach = info;
        lion = new int[11];
        int score = 0;
        for (int i = 0; i < 11; i++) {
            if (info[i] > 0) {
                score -= (10 - i);  // 점수차
            }
        }

        dfs(0, score, 10);
        if (!finish) {
            result = new int[]{-1};
        }
        return result;
    }

    static void dfs(int count, int score, int idx) {
        if (count == n) {
            if (score > max) {
                max = score;
                result = lion.clone();
                finish = true;
            }
            return;
        }

        if (idx == -1) {
            return;
        }

        // 현재 idx 맞출 때 (이김)
        int pScore = apeach[idx];
        lion[idx] = pScore + 1;
        if (pScore > 0) {
            dfs(count + lion[idx], score + (10 - idx) * 2, idx - 1);
        } else {
            dfs(count + 1, score + 10 - idx, idx - 1);
        }

        // 현재 idx를 맞추지만 어피치보다 적게 맞출 때 (질때)
        for (int i = pScore; i >= 0; i--) {
            lion[idx] = i;
            dfs(count + i, score, idx - 1);
        }
    }
}
