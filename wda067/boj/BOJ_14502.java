import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준
public class BOJ_14502 {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private static int[][] map;
    private static int N, M, max;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        recur(0);
        System.out.println(max);
    }

    //3개의 벽을 세우는 모든 경우의 수 탐색
    private static void recur(int count) {
        if (count == 3) {
            //2차원 배열 깊은 복사
            int[][] clone = new int[N][M];
            for (int i = 0; i < N; i++) {
                clone[i] = map[i].clone();
            }
            //최대값 갱신
            max = Math.max(max, bfs(clone));
            return;
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    recur(count + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    //BFS 후 안전한 칸의 개수를 반환
    private static int bfs(int[][] map) {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextR = curR + DR[i];
                int nextC = curC + DC[i];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }

                if (map[nextR][nextC] == 0) {
                    queue.add(new int[]{nextR, nextC});
                    map[nextR][nextC] = 2;
                }
            }
        }

        return (int) Arrays.stream(map)
                .flatMapToInt(Arrays::stream)
                .filter(i -> i == 0)
                .count();
    }
}
