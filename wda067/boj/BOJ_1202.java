import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 보석 도둑 / 골드2
https://www.acmicpc.net/problem/1202
 */
public class BOJ_1202 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewelry[] jewelries = new Jewelry[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            jewelries[i] = new Jewelry(M, V);
        }

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            int C = Integer.parseInt(br.readLine());
            bags[i] = C;
        }

        Arrays.sort(jewelries, Comparator.comparingInt((Jewelry j) -> j.weight));
        Arrays.sort(bags);

        //가치가 큰 보석부터 뽑기
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        long result = 0;
        int idx = 0;

        for (int i = 0; i < K; i++) {

            //현재 가방에 넣을 수 있는 보석들 모두 추가
            while (idx < N && jewelries[idx].weight <= bags[i]) {
                pq.add(jewelries[idx].value);
                idx++;
            }

            //가장 가치가 높은 보석 하나 선택
            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }

        System.out.println(result);
    }

    private static class Jewelry {
        int weight, value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
