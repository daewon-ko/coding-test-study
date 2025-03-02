package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2141 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] houses = new long[N][2];
        StringTokenizer st;
        long total = 0L;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long pos = Long.parseLong(st.nextToken());
            long people = Long.parseLong(st.nextToken());
            houses[i][0] = pos;
            houses[i][1] = people;
            total += people;
        }
        long mid = (total + 1) / 2;
        Arrays.sort(houses, (h1, h2) -> Long.compare(h1[0], h2[0]));
        long res = 0L;
        for (int i = 0; i < N; i++) {
            res += houses[i][1];
            if (mid <= res) {
                System.out.println(houses[i][0]);
                return;
            }
        }

    }
}
