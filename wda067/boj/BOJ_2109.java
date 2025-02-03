import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 순회강연 / 골드3
https://www.acmicpc.net/problem/2109
 */
public class BOJ_2109 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Lecture> lectures = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(p, d));
        }

        lectures.sort(Comparator.comparingInt(o -> o.day));  //강연을 일자를 기준으로 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>();  //강연료를 오름차순으로 저장

        for (Lecture lecture : lectures) {
            pq.add(lecture.pay);
            //가능한 날짜 수를 초과했다면, 강연료가 가장 낮은 강의를 제거
            if (pq.size() > lecture.day) {
                pq.poll();
            }
        }

        int totalPay = pq.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(totalPay);
    }

    private static class Lecture {
        int pay, day;

        public Lecture(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }
    }
}
