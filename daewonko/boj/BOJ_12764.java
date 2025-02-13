package daewonko.boj;

import java.io.*;
import java.util.*;

public class BOJ_12764 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            PriorityQueue<Integer> available = new PriorityQueue<>();
            List<int[]> usage = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                pq.offer(new int[]{start, end});
            }

            int maxComputer = 0;
            int[] count = new int[n];
            PriorityQueue<int[]> occupied = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                while (!occupied.isEmpty() && occupied.peek()[0] <= current[0]) {
                    available.offer(occupied.poll()[1]);
                }

                int computer;
                if (available.isEmpty()) {
                    computer = maxComputer++;
                } else {
                    computer = available.poll();
                }

                count[computer]++;
                occupied.offer(new int[]{current[1], computer});
            }

            System.out.println(maxComputer);
            for (int i = 0; i < maxComputer; i++) {
                System.out.print(count[i] + " ");
            }
        }
    }

