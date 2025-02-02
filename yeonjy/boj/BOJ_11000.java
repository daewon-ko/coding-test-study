package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11000 {
    static class Lecture {
        int start;
        int finish;

        public Lecture(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Lecture[] lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //시작시간이 같으면 끝나는 시간 기준 오름차순으로 정렬, 시작시간이 다르면 시작시간 오름차순으로 정렬
        Arrays.sort(lectures, (l1, l2) -> {
            if (l1.start == l2.start) {
                return l1.finish - l2.finish;
            } else {
                return l1.start - l2.start;
            }});

        //우선순위 큐에는 끝나는 시간만 들어가며, 오름차순으로 자동 정렬됨
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].finish);

        for (int i = 1; i < N; i++) {
            if (pq.peek() <= lectures[i].start) {  //현재 우선순위 큐에서 가장 작은 종료 시간과 i번째 강의 시작 시간 비교
                pq.poll();  //i번째 강의 시작 시간이 우선순위 큐에서 가장 작은 종료시간보다 크면 우선순위 큐에서 가장 작은 종료 시간 poll
            }
            pq.offer(lectures[i].finish);  //pq에 lecture[i].finish 넣기
            //수업을 현재 존재하는 강의실에 넣을 수 있으면 pq가 안 늘어남
        }

        System.out.println(pq.size());
    }
}
