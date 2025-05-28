import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 뱀 / 골드4
https://www.acmicpc.net/problem/3190
 */
public class BOJ_3190 {

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    private static boolean[][] apple;
    private static int N, answer = Integer.MAX_VALUE;
    private static Map<Integer, String> map = new HashMap<>();  //시간 -> 방향
    private static Queue<int[]> snake = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  //100
        int K = Integer.parseInt(br.readLine());  //100
        apple = new boolean[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            apple[r][c] = true;
        }

        int L = Integer.parseInt(br.readLine());  //100
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());  //10,000
            String C = st.nextToken();  //L: 왼쪽, D: 오른쪽
            map.put(X, C);
        }

        snake.add(new int[]{1, 1});  //시작 위치
        move(1, 2, 1, 1);  //처음에는 오른쪽으로 이동 (1, 1) -> (1, 2)
        System.out.println(answer);
    }

    private static void move(int r, int c, int dir, int time) {
        if (isOutOfRange(r, c)) {  //벽에 부딪힐 경우
            answer = time;
            return;
        }

        for (int[] pos : snake) {
            if (Arrays.equals(pos, new int[]{r, c})) {  //자기 자신과 부딪힐 경우
                answer = time;
                return;
            }
        }

        snake.add(new int[]{r, c});  //(r, c)로 이동

        if (apple[r][c]) {  //사과가 있다면 몸 길이를 늘린다
            apple[r][c] = false;
        } else {  //사과가 없다면 몸 길이는 유지한 채 이동
            snake.poll();
        }

        if (map.containsKey(time)) {  //이동한 뒤 방향 회전이 가능할 경우
            String s = map.get(time);
            if (s.equals("L")) {  //왼쪽
                dir = (dir + 3) % 4;
            } else if (s.equals("D")) {  //오른쪽
                dir = (dir + 1) % 4;
            }
        }

        //다음 좌표
        int nr = r + dr[dir];
        int nc = c + dc[dir];

        move(nr, nc, dir, time + 1);
    }


    private static boolean isOutOfRange(int r, int c) {
        return r < 1 || r > N || c < 1 || c > N;
    }
}
