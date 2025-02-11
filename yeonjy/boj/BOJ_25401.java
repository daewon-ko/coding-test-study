package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_25401 {
    static int N;
    static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                double diff = (double) (cards[i] - cards[j]) / (i - j);
                if (diff == (int) diff) {
                    res = Math.min(res, getCount(cards[i] - i * (int) diff, (int) diff));
                }
            }
        }
        System.out.println(res);
    }

    static int getCount(int num, int diff) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (cards[i] != num) {
                count++;
            }
            num += diff;
        }
        return count;
    }
}
