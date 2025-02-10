package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 평범한 배낭

public class BOJ_12865{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];

        int[] weight = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = k; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        System.out.println(dp[k]);
    }

}
 class BOJ_12865_FAIL {
    static int n,k,w,v;
    static int MAX = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        PriorityQueue<Product> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            pq.add(new Product(w, v));
        }

        while (!pq.isEmpty()) {

            int value = 0;
            Product product = pq.poll();
            value+= product.value;

            while (!pq.isEmpty() && pq.peek().weight + product.weight <= k) {
                Product poll = pq.poll();
                value += poll.value;
            }

            MAX = Math.max(value, MAX);
        }
        System.out.println(MAX);

    }

    static class Product implements Comparable<Product>{
        int weight;
        int value;

        public Product(final int weight, final int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Product other) {
            if (this.value != other.value) {
                return other.value - this.value;
            }else {
                return this.weight - other.weight;
            }
        }
    }
}
