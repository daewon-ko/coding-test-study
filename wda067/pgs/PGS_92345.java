/*
프로그래머스 / 사라지는 발판 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/92345
 */
class PGS_92345 {

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private int N, M;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        N = board.length;
        M = board[0].length;
        return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1])[1];  //[승리 여부, 턴 수]
    }

    private int[] dfs(int[][] board, int ar, int ac, int br, int bc) {
        if (board[ar][ac] == 0) {  //현재 위치가 이미 사라진 경우 → 패배
            return new int[]{0, 0};
        }

        boolean canWin = false;
        int minTurns = Integer.MAX_VALUE;
        int maxTurns = 0;

        for (int dir = 0; dir < 4; dir++) {
            int nr = ar + dr[dir];
            int nc = ac + dc[dir];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                continue;
            }
            if (board[nr][nc] == 0) {
                continue;
            }

            board[ar][ac] = 0;  //현재 위치 발판 제거
            int[] result = dfs(board, br, bc, nr, nc);  //상대 차례
            board[ar][ac] = 1;  //백트래킹

            if (result[0] == 0) {  //상대가 지면 내가 이긴 것
                canWin = true;
                minTurns = Math.min(minTurns, result[1] + 1);
            } else if (!canWin) {  //상대가 이기면 최대한 오래 버티기
                maxTurns = Math.max(maxTurns, result[1] + 1);
            }
        }

        if (canWin) {
            return new int[]{1, minTurns};
        } else {
            return new int[]{0, maxTurns};
        }
    }
}
