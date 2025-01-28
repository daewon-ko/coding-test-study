package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 강의실 배정
public class BOJ_11000 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        List<Lecture> lectures = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end =  Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(start, end));
        }

        Collections.sort(lectures);

        // 종료시간을 pq에 넣는다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Lecture lecture : lectures) {
            if (!pq.isEmpty()) {
                if (pq.peek() <= lecture.start) {
                    pq.poll();
                }
            }

            pq.add(lecture.end);

        }

        System.out.println(pq.size());
    }
    static class Lecture implements Comparable<Lecture>{
        int start;
        int end;

        public Lecture(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture other) {

            // start를 기준으로 오름차순
            if (this.start != other.start) {
                return this.start - other.start;
            }

            // 아닐 경우, end를 기준으로 오름차순
            return this.end - other.end;

        }
    }
}
