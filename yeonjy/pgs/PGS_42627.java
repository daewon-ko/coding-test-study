package yeonjy.pgs;

import java.util.*;

public class PGS_42627 {
    static class Job implements Comparable<Job> {
        int num;
        int request;
        int time;

        public Job(int num, int request, int time) {
            this.num = num;
            this.request = request;
            this.time = time;
        }

        @Override
        public int compareTo(Job job) {
            if (this.time != job.time) {
                return Integer.compare(this.time, job.time);
            }
            if (this.request != job.request) {
                return Integer.compare(this.request, job.request);
            }
            return Integer.compare(this.num, job.num);
        }
    }

    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        PriorityQueue<Job> availableQueue = new PriorityQueue<>();
        int currentTime = 0;
        int totalTurnaround = 0;
        int index = 0;

        while (index < jobs.length || !availableQueue.isEmpty()) {
            while (index < jobs.length && jobs[index][0] <= currentTime) {
                availableQueue.add(new Job(index, jobs[index][0], jobs[index][1]));
                index++;
            }

            if (availableQueue.isEmpty()) {
                currentTime = jobs[index][0];
            } else {
                Job job = availableQueue.poll();
                currentTime += job.time;
                totalTurnaround += currentTime - job.request;
            }
        }

        return totalTurnaround / jobs.length;
    }
}
