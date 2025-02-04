package daewonko.boj;

import java.util.PriorityQueue;
import java.util.Scanner;

// 백준 공주님의 정원
public class BOJ_2457 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


        PriorityQueue<Calendar> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int startMonth = sc.nextInt();
            int startDay = sc.nextInt();
            int endMonth = sc.nextInt();
            int endDay = sc.nextInt();

            int startDate = startMonth * 100 + startDay;
            int endDate = endMonth * 100 + endDay;
            queue.offer(new Calendar(startDate, endDate));
        }

        int startDate = 301;
        int endDate = 1130;

        int lastEnd = 0;
        int count = 0;

        while (!queue.isEmpty()) {
            boolean found = false;
            int maxEnd = 0;

            while (!queue.isEmpty() && queue.peek().startDate <= startDate) {
                Calendar poll = queue.poll();
                maxEnd = Math.max(maxEnd, poll.endDate);
                found = true;
            }

            if (!found) {
                break;
            }

            startDate = maxEnd;
            count++;

            if (startDate > endDate) {
                System.out.println(count);
                return;
            }
        }

        System.out.println(0);

    }

    static class Calendar implements Comparable<Calendar> {
        int startDate;
        int endDate;

        public Calendar(int startDate, int endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public int compareTo(Calendar o) {
            if (this.startDate != o.startDate) {
                return this.startDate - o.startDate;
            } else {
                return o.endDate - this.endDate;
            }


        }
    }
}
