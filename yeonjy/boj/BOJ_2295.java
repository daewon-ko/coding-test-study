package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2295 {
    static long[] arr;
    static long[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new long[N];
        sum = new long[N * N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr);

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sum[cnt++] = arr[i] + arr[j];
            }
        }
        Arrays.sort(sum, 0, cnt - 1);

        for (int i = N - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (Arrays.binarySearch(sum, 0, cnt - 1, arr[i] - arr[j]) < 0) {
                    continue;
                }
                System.out.println(arr[i]);
                return;
            }
        }
    }
}
