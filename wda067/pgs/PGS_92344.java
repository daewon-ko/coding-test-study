/*
프로그래머스 / 파괴되지 않은 건물 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/92344
 */
class PGS_92344 {

    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        int[][] changes = new int[N + 1][M + 1];

        //변화량 기록
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            int degree = s[5];

            if (type == 1) {
                degree *= -1;
            }

            changes[r1][c1] += degree;  //좌측 상단
            changes[r1][c2 + 1] -= degree;  //우측 상단
            changes[r2 + 1][c1] -= degree;  //좌측 하단
            changes[r2 + 1][c2 + 1] += degree;  //우측 하단
        }

        //행 방향 누적 합
        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                changes[i][j] += changes[i][j - 1];
            }
        }

        //열 방향 누적 합
        for (int j = 0; j <= M; j++) {
            for (int i = 1; i <= N; i++) {
                changes[i][j] += changes[i - 1][j];
            }
        }

        //맵 업데이트 및 결과 계산
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] += changes[i][j];
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}

