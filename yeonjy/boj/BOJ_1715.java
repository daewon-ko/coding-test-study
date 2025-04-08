package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> cards = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            cards.add(Integer.parseInt(br.readLine()));
        }
        long total = 0;

        while (cards.size() > 1) {
            for (int i = 0; i < cards.size() - 1; i++) {
                int a = cards.poll();
                int b = cards.poll();
                total += a + b;
                cards.add(a + b);
            }
        }
        System.out.println(total);
    }
}
