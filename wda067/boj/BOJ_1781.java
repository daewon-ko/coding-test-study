import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 컵라면 / 골드2
https://www.acmicpc.net/problem/1781
 */
public class BOJ_1781 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Problem[] problems = new Problem[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            problems[i] = new Problem(deadline, ramen);
        }

        Arrays.sort(problems, Comparator.comparingInt(p -> p.deadline));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Problem p : problems) {
            pq.offer(p.ramen);
            if (pq.size() > p.deadline) {
                pq.poll();
            }
        }

        long totalRamen = 0;
        while (!pq.isEmpty()) {
            totalRamen += pq.poll();
        }

        System.out.println(totalRamen);
    }

    private static class Problem {
        int deadline, ramen;

        Problem(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }
    }
}

