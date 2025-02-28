import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 점프 게임 / 골드5
https://www.acmicpc.net/problem/15558
 */
public class BOJ_15558 {

    private static int N, K;
    private static int[] left, right;
    private static int[] dx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  //1 ~ 100,000
        K = Integer.parseInt(st.nextToken());  //1 ~ 100,000

        left = new int[N];
        right = new int[N];
        dx = new int[]{-1, 1, K};

        char[] charArray1 = br.readLine().toCharArray();
        char[] charArray2 = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            left[i] = charArray1[i] - '0';
            right[i] = charArray2[i] - '0';
        }

        boolean flag = bfs();
        if (flag) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean bfs() {
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(0, 0, true));
        boolean[][] visited = new boolean[N][2];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Position cur = q.poll();

            for (int dir = 0; dir < 3; dir++) {
                int next = cur.x + dx[dir];
                boolean isLeft = cur.isLeft;

                if (dir == 2) {  //다른 줄로 이동
                    isLeft = !isLeft;
                }

                if (next <= cur.t) {
                    continue;
                }

                if (isLeft) {  //왼쪽 줄일 때
                    if (next <= N - 1) {
                        if (left[next] == 0) {  //위험한 칸일 경우
                            continue;
                        }
                        if (!visited[next][0]) {  //이동가능할 경우
                            q.add(new Position(next, cur.t + 1, isLeft));
                            visited[next][0] = true;
                        }
                    }
                }

                if (!isLeft) {  //오른쪽 줄일 때
                    if (next <= N - 1) {
                        if (right[next] == 0) {  //위험한 칸일 경우
                            continue;
                        }
                        if (!visited[next][1]) {  //이동가능할 경우
                            q.add(new Position(next, cur.t + 1, isLeft));
                            visited[next][1] = true;
                        }
                    }
                }

                if (next >= N - 1) {  //이동할 수 있는데 이미 탈출한 경우
                    return true;
                }
            }
        }

        return false;
    }

    private static class Position {

        private int x, t;
        private boolean isLeft;

        public Position(int x, int t, boolean isLeft) {
            this.x = x;
            this.t = t;
            this.isLeft = isLeft;
        }
    }
}
