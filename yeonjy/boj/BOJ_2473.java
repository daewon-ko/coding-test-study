package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2473 {
    static int N;
    static int[] arr;
    static long min;
    static long sumResult;
    static int[] tmpResult;
    static int[] result;

    public static void main(String[] args) throws IOException {
        tmpResult = new int[3];
        result = new int[3];
        sumResult= Long.MAX_VALUE;


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < N - 2; i++) {
            min = Long.MAX_VALUE;
            binarySearch(i, i + 1, N - 1);

            if (sumResult > min) {
                sumResult = min;
                result = tmpResult.clone();
            }
        }

        System.out.println(arr[result[0]] + " " + arr[result[1]] + " " + arr[result[2]]);
    }

    static void binarySearch(int idx, int left, int right) {
        if (left >= right) {
            return;
        }

        long sum = arr[left] + arr[right] + (long)arr[idx];
        if (min > Math.abs(sum)) {
            min = Math.abs(sum);
            tmpResult[0] = idx;
            tmpResult[1] = left;
            tmpResult[2] = right;
        }
        if (sum > 0) {  //합이 양수일 때
            binarySearch(idx, left, right - 1);
        } else if (sum < 0) {  //합이 음수거나 0일 때
            binarySearch(idx, left + 1, right);
        }
    }
}
