package yeonjy.pgs;

class PGS_92344 {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        int[][] sum = new int[N + 1][M + 1];
        
        for (int[] effect : skill) {
            int r1 = effect[1];
            int r2 = effect[3];
            int c1 = effect[2];
            int c2 = effect[4];
            int power = effect[5];
            if (effect[0] == 1) {
                power *= -1;
            }
            sum[r1][c1] += power;
            sum[r2 + 1][c2 + 1] += power;
            sum[r1][c2 + 1] -= power;
            sum[r2 + 1][c1] -= power;
        }
        
        // up -> down
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum[i + 1][j] += sum[i][j];
            }
        }
        
        // left -> right
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum[i][j + 1] += sum[i][j];
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + sum[i][j] > 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
