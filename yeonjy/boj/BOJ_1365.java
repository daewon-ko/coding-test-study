package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1365 {
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // solution
        dp[1] = Integer.MIN_VALUE;
        int size = 0;
        int idx = 1;

        for (int i = 1; i <= N; i++) {
            if (arr[i] > dp[size]) {
                dp[++size] = arr[i];
            } else {
                idx = binarySearch(0, size, arr[i]);
                dp[idx] = arr[i];
            }
        }
        System.out.println(N - size);
    }

    static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (dp[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
