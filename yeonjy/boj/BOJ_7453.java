package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_7453 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] arrs = new long[4][N];
        for (int i = 0; i < N; i++) {
            long[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
            for (int j = 0; j < 4; j++) {
                arrs[j][i] = arr[j];
            }
        }

        int N2 = N * N;
        long[] arr1 = new long[N2];
        long[] arr2 = new long[N2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr1[N * i + j] = arrs[0][i] + arrs[1][j];
                arr2[N * i + j] = arrs[2][i] + arrs[3][j];
            }
        }

        Arrays.sort(arr2);
        long answer = 0;
        for (int i = 0; i < N2; i++) {
            answer += binarySearch(arr1[i], arr2, N2);
        }

        System.out.println(answer);
    }

    public static int binarySearch(long num, long[] arr, int N) {
        int l1 = 0;
        int r1 = N - 1;
        int m1;
        while (l1 < r1) {
            m1 = (l1 + r1) / 2;
            long added = num + arr[m1];
            if (added >= 0) {
                r1 = m1;
            } else {
                l1 = m1 + 1;
            }
        }
        if (num + arr[r1] != 0) {
            return 0;
        }

        int l2 = 0;
        int r2 = N - 1;
        int m2;
        while (l2 < r2) {
            m2 = (l2 + r2) / 2;
            long added = num + arr[m2];
            if (added > 0) {
                r2 = m2;
            } else {
                l2 = m2 + 1;
            }
        }
        if (num + arr[r2] == 0) {
            r2++;
        }

        return r2 - r1;
    }
}
