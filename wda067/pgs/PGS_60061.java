/*
프로그래머스 / 기둥과 보 설치 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/60061
 */
class PGS_60061 {

    private boolean[][] pillars;
    private boolean[][] beams;
    private int n;

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        pillars = new boolean[n + 1][n + 1];
        beams = new boolean[n + 1][n + 1];

        int count = 0;

        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int type = frame[2]; // 0: 기둥, 1: 보
            int action = frame[3]; // 0: 삭제, 1: 설치

            if (action == 1) { // 설치
                if (type == 0) { // 기둥 설치
                    if (canInstallPillar(x, y)) {
                        pillars[x][y] = true;
                        count++;
                    }
                } else { // 보 설치
                    if (canInstallBeam(x, y)) {
                        beams[x][y] = true;
                        count++;
                    }
                }
            } else { // 삭제
                if (type == 0) { // 기둥 삭제
                    pillars[x][y] = false;
                } else { // 보 삭제
                    beams[x][y] = false;
                }
                if (!isValidStructure()) { // 삭제 후 구조물이 유효하지 않으면 복구
                    if (type == 0) {
                        pillars[x][y] = true;
                    } else {
                        beams[x][y] = true;
                    }
                } else {
                    count--;
                }
            }
        }

        int[][] answer = new int[count][3];
        int index = 0;

        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillars[x][y]) {
                    answer[index++] = new int[]{x, y, 0};
                }
                if (beams[x][y]) {
                    answer[index++] = new int[]{x, y, 1};
                }
            }
        }

        return answer;
    }

    private boolean canInstallPillar(int x, int y) {
        return y == 0 || pillars[x][y - 1] || (x > 0 && beams[x - 1][y]) || beams[x][y];
    }

    private boolean canInstallBeam(int x, int y) {
        return (y > 0 && pillars[x][y - 1]) || (y > 0 && pillars[x + 1][y - 1]) || (x > 0 && beams[x - 1][y] && beams[x + 1][y]);
    }

    private boolean isValidStructure() {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillars[x][y] && !canInstallPillar(x, y)) {
                    return false;
                }
                if (beams[x][y] && !canInstallBeam(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}



