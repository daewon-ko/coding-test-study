import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 강의실 배정 / 골드5
https://www.acmicpc.net/problem/11000
 */
public class BOJ_11000 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //강의 시작 시간을 기준으로 정렬
        PriorityQueue<Lecture> lectures = new PriorityQueue<>((o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;  //시작 시간이 같으면 종료 시간으로 정렬
            }
            return o1.start - o2.start;  //오름차순
        });

        int N = Integer.parseInt(br.readLine());

        //모든 수업 우선순위 큐에 삽입
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(start, end));
        }

        PriorityQueue<Integer> endTimes = new PriorityQueue<>();  //종료 시간만 관리

        while (!lectures.isEmpty()) {
            Lecture lecture = lectures.poll();

            if (!endTimes.isEmpty() && lecture.start >= endTimes.peek()) {  //기존 강의실 사용 가능
                endTimes.poll();
            }

            endTimes.add(lecture.end);  //종료 시간 추가
        }

        System.out.println(endTimes.size());
    }

    private static class Lecture {
        int start;
        int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
