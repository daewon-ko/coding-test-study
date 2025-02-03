import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 경사로 / 골드3
https://www.acmicpc.net/problem/14890
 */
public class BOJ_14890 {

    private static int[][] map;
    private static boolean[] checked;
    private static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (isPathValid(map[i])) {
                count++;
            }
            if (isPathValid(getColumn(i))) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static boolean isPathValid(int[] path) {
        checked = new boolean[N];

        for (int i = 1; i < N; i++) {
            if (path[i] == path[i - 1]) {
                continue;
            }

            if (path[i] == path[i - 1] + 1) {
                if (canNotPlaceSlope(path, i - L, i - 1, path[i - 1])) {
                    return false;
                }
            } else if (path[i] == path[i - 1] - 1) {
                if (canNotPlaceSlope(path, i, i + L - 1, path[i])) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    private static boolean canNotPlaceSlope(int[] path, int start, int end, int height) {
        if (start < 0 || end >= N) {
            return true;
        }

        for (int i = start; i <= end; i++) {
            if (checked[i] || path[i] != height) {
                return true;
            }
        }

        for (int i = start; i <= end; i++) {
            checked[i] = true;
        }

        return false;
    }

    private static int[] getColumn(int colIndex) {
        int[] column = new int[N];
        for (int i = 0; i < N; i++) {
            column[i] = map[i][colIndex];
        }
        return column;
    }
}
