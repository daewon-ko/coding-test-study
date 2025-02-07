package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983 {
    static int M;
    static int N;
    static int L;
    static int[] hunters;
    static int[][] animals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());  // 사대 수
        N = Integer.parseInt(st.nextToken());  // 동물 수
        L = Integer.parseInt(st.nextToken());  // 사냥 거리

        st = new StringTokenizer(br.readLine());
        hunters = new int[M];
        for (int i = 0; i < M; i++) {
            hunters[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(hunters);

        animals = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            animals[i][0] = Integer.parseInt(st.nextToken());
            animals[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for (int[] animal : animals) {
            if (isPossible(animal[0], animal[1])) {
                result++;
            }
        }
        System.out.println(result);
    }

    static boolean isPossible(int x, int y) {
        int start = 0;
        int end = M - 1;
        int mid = 0;
        int distance = 0;

        while (start <= end) {
            mid = (start + end) / 2;
            distance = Math.abs(hunters[mid] - x) + y;
            if (distance <= L) {
                return true;
            }
            if (x == hunters[mid]) {  // x 좌표가 같은데 사냥을 못하면 y가 L보다 큰 것. -> 사냥 불가
                return false;
            }
            if (x < hunters[mid]) {
                end = mid - 1;
            } else if (x > hunters[mid]) {
                start = mid + 1;
            }
        }
        return false;
    }
}
