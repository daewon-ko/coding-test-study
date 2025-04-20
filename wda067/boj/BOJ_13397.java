import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 구간 나누기 2 / 골드4
https://www.acmicpc.net/problem/13397
 */
public class BOJ_13397 {

    private static int[] arr;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxVal = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxVal = Math.max(maxVal, arr[i]);
        }

        int left = 0;
        int right = maxVal;
        int answer = right;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canDivide(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean canDivide(int diff) {
        int count = 1;
        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if (max - min > diff) {
                count++;
                min = arr[i];
                max = arr[i];
            }
        }

        return count <= M;
    }
}
