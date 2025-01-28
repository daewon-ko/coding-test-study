package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 순회 강연
public class BOJ_2109 {
    static int n;
    static int pay;
    static int day;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;


        List<Schedule> list = new ArrayList<>();

        int max = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            pay = Integer.parseInt(st.nextToken());
            day = Integer.parseInt(st.nextToken());
            list.add(new Schedule(pay, day));
            max = Math.max(max, day);
        }

        Queue<Schedule> queue = new PriorityQueue<>();

        for (Schedule s : list) {
            queue.add(s);
        }


        boolean[] visited = new boolean[max+ 1];
        int result = 0;
        while (!queue.isEmpty()) {
            Schedule poll = queue.poll();

            for (int i = poll.day; i > 0; i--) {
                if (!visited[i]) {
                    visited[i] = true;
                    result += poll.pay;
                    break;
                }
            }
        }

        System.out.println(result);


    }

    static class Schedule implements Comparable<Schedule>{
        int pay;
        int day;

        public Schedule(int pay, int day) {

            this.pay = pay;
            this.day = day;
        }

        @Override
        public int compareTo(Schedule o) {
            // pay는 내림차순
            if (this.pay != o.pay) {
                return o.pay - this.pay;
            } else
            // 날짜는 오름차순
                return this.day - o.day;
        }
    }
}
