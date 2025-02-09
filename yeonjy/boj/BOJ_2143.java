package yeonjy.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2143 {

    static int T, N, M;
    static long ansCnt;
    static int[] numsA, numsB, sumA, sumB, partA, partB;
    static String[] line;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        numsA = new int[N];
        sumA = new int[N + 1];


        line = br.readLine().split(" ");

        for(int i = 0; i < N; i++) {
            numsA[i] = Integer.parseInt(line[i]);
            // 누적합
            sumA[i + 1] = sumA[i] + numsA[i];
        }

        // 부 배열의 합
        int temp = 0;
        partA = new int[((N + 1) * (N)) / 2];
        for(int cnt = 1; cnt <= N; cnt++) {
            for(int st = 0; st + cnt <= N; st++) {
                partA[temp++] = sumA[st + cnt] - sumA[st];
            }
        }

        M = Integer.parseInt(br.readLine());
        numsB = new int[M];
        sumB = new int[M + 1];

        line = br.readLine().split(" ");

        for(int i = 0; i < M; i++) {
            numsB[i] = Integer.parseInt(line[i]);
            // 누적합
            sumB[i + 1] = sumB[i] + numsB[i];
        }

        // 부 배열의 합
        temp = 0;
        partB = new int[((M + 1) * (M)) / 2];
        for(int cnt = 1; cnt <= M; cnt++) {
            for(int st = 0; st + cnt <= M; st++) {
                partB[temp++] = sumB[st + cnt] - sumB[st];
            }
        }

        Arrays.sort(partA);
        Arrays.sort(partB);

        for(int i = 0; i < partA.length; i++) {
            int aValue = partA[i];
            int bValue = T - aValue;

            int bValueCnt = upperBound(partB, bValue) - lowerBound(partB, bValue);

            ansCnt += bValueCnt;
        }

        System.out.println(ansCnt);
    }

    private static int lowerBound(int[] arr, int key) {
        int l = 0;
        int r = arr.length;

        while(l < r) {
            int mid = (l + r) / 2;
            if(arr[mid] < key)
                l = mid + 1;
            else
                r = mid;
        }

        return r;
    }

    private static int upperBound(int[] arr, int key) {
        int l = 0;
        int r = arr.length;

        while(l < r) {
            int mid = (l + r) / 2;
            if(arr[mid] <= key)
                l = mid + 1;
            else
                r = mid;
        }

        return r;
    }
}