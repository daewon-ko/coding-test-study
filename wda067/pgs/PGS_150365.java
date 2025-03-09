/*
프로그래머스 / 미로 탈출 명령어 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/150365
 */
class PGS_150365 {

    private static int[] dr = {1, 0, 0, -1};  //d, l, r, u 순서
    private static int[] dc = {0, -1, 1, 0};
    private static char[] dir = {'d', 'l', 'r', 'u'};

    private static int n, m;
    private static int[] start, end;
    private static String answer;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        start = new int[]{x, y};
        end = new int[]{r, c};

        int minDist = Math.abs(r - x) + Math.abs(c - y);  //맨해튼 거리
        if (k < minDist || (k - minDist) % 2 != 0) {  //k가 최소 거리보다 작거나, 남은 거리가 홀수일 경우
            return "impossible";
        }

        dfs(start, k, new StringBuilder());

        return answer;
    }

    //도달 가능 유무를 리턴
    private static boolean dfs(int[] cur, int remain, StringBuilder path) {
        int dist = Math.abs(cur[0] - end[0]) + Math.abs(cur[1] - end[1]);
        if (remain < dist) {  //도달 불가능한 경우
            return false;
        }

        if (remain == 0) {  //이동 완료 후
            if (cur[0] == end[0] && cur[1] == end[1]) {  //도착 지점에 도착했을 경우 리턴
                answer = path.toString();
                return true;
            }
            return false;  //도착 못하면 false
        }

        for (int d = 0; d < 4; d++) {  //사전 순 탐색
            int nextR = cur[0] + dr[d];
            int nextC = cur[1] + dc[d];

            if (nextR <= 0 || nextR > n || nextC <= 0 || nextC > m) {
                continue;
            }

            path.append(dir[d]);
            if (dfs(new int[]{nextR, nextC}, remain - 1, path)) {
                return true;
            }
            path.deleteCharAt(path.length() - 1);  //백트래킹
        }

        return false;
    }
}