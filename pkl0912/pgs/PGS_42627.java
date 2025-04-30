package pkl0912.pgs;

import java.util.*;

public class PGS_42627 {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0])); 

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1])); 

        int time = 0;
        int idx = 0;
        int total = 0;
        int count = 0;

        while (count < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= time) {
                pq.offer(jobs[idx]);
                idx++;
            }

            if (!pq.isEmpty()) {
                int[] job = pq.poll();
                time += job[1]; 
                total += (time - job[0]); 
                count++;
            } else {
                time++; 
            }
        }

        return total / jobs.length;
    }
}