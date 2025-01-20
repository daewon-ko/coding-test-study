package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_21608 {
    static final int ONE = 1;
    static final int TWO = 10;
    static final int THREE = 100;
    static final int FOUR = 1000;

    static final int[] dx = new int[]{-1, 1, 0, 0};
    static final int[] dy = new int[]{0, 0, -1, 1};

    static int N;
    static int[][] map;
    static int[][] likeFriendNums;
    static int[][] emptyNums;
    static List<Integer>[] likeFriends;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int studentNum = N * N;

        map = new int[N][N];
        likeFriendNums = new int[N][N];
        emptyNums = new int[N][N];
        likeFriends = new List[studentNum + 1];
        int[] priority = new int[studentNum];
        for (int i = 1; i <= studentNum; i++) {
            likeFriends[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < studentNum; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            priority[i] = student;
            for (int j = 0; j < 4; j++) {
                likeFriends[student].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < studentNum; i++) {
            int[] seat = checkFirst(priority[i]);
            map[seat[0]][seat[1]] = priority[i];
        }

        long result = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                int cnt = 0;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (isValid(nx, ny) && likeFriends[map[x][y]].contains(map[nx][ny])) {
                        cnt++;
                    }
                }
                if (cnt == 1) {
                    result += ONE;
                } else if (cnt == 2) {
                    result += TWO;
                } else if (cnt == 3) {
                    result += THREE;
                } else if (cnt == 4) {
                    result += FOUR;
                }
            }
        }
        System.out.println(result);
    }

    static int[] checkFirst(int student) {
        likeFriendNums = new int[N][N];
        int maxFriends = 0;
        int maxCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int friendCnt = 0;
                if (map[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (isValid(x, y) && map[x][y] != 0
                                && likeFriends[student].contains(map[x][y])) {  // 갈 수 있는 좌표 && 학생이 배치된 자리일 때 && 친구가 인접한 곳에 있을 때
                            friendCnt++;
                        }
                    }
                    if (maxFriends < friendCnt) {
                        maxFriends = friendCnt;
                        maxCnt = 1;
                    } else if (maxFriends == friendCnt && friendCnt != 0) {
                        maxCnt++;
                    }
                }
                likeFriendNums[i][j] = friendCnt;
            }
        }
        if (maxCnt == 1) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (likeFriendNums[i][j] == maxFriends) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return checkSecond(maxFriends);
    }

    static int[] checkSecond(int maxFriends) {
        emptyNums = new int[N][N];
        int maxEmptyNum = 0;
        int rx = -1, ry = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int emptyNum = 0;
                if (map[i][j] == 0 && likeFriendNums[i][j] == maxFriends) {
                    if (rx == -1) {
                        rx = i;
                        ry = j;
                    }
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (isValid(x, y) && map[x][y] == 0) {
                            emptyNum++;
                        }
                    }
                    emptyNums[i][j] = emptyNum;
                    if (maxEmptyNum < emptyNum) {
                        maxEmptyNum = emptyNum;
                    }
                }
            }
        }
        if (maxEmptyNum == 0) {
            if (maxFriends >= 1) {
                return new int[]{rx, ry};
            }
            return third();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (emptyNums[i][j] == maxEmptyNum) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    static int[] third() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
