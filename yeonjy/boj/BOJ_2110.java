package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    static int N;
    static int[] houses;
    static long left;
    static long right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        houses = new int[N];
        int C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        left = 1;
        right = houses[N - 1];
        long mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            int loc = 0;
            int cnt = 1;
            for (int i = 1; i < N; i++) {
                if (houses[i] - houses[loc] >= mid) {
                    cnt++;
                    loc = i;
                }
            }

            if (cnt < C) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left - 1);
    }
}

