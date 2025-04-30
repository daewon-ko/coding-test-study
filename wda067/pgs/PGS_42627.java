import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
프로그래머스 / 디스크 컨트롤러 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/42627
 */
class PGS_42627 {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int currentTime = 0;
        int totalWaitTime = 0;
        int jobIndex = 0;
        int completedJobs = 0;   

        while (completedJobs < jobs.length) {
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= currentTime) {
                pq.add(jobs[jobIndex]);
                jobIndex++;
            }

            if (!pq.isEmpty()) {
                int[] currentJob = pq.poll();
                currentTime += currentJob[1];
                totalWaitTime += currentTime - currentJob[0];
                completedJobs++;
            } else {
                currentTime = jobs[jobIndex][0];
            }
        }

        return totalWaitTime / jobs.length;
    }
}
