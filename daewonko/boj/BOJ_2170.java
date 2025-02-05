package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 백준 선 긋기
public class BOJ_2170 {

    static int n;
    static List<Line> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = null;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lines.add(new Line(start, end));
        }

        Collections.sort(lines);


        int sum = 0;


        int start = lines.get(0).start;
        int end = lines.get(0).end;
        for (int i = 1; i < n; i++) {
            Line curLine = lines.get(i);
            // 겹치는 선분일 경우에는 end값으로 , 즉 긴 선분으로 초기화한다.
            if (curLine.start <= end) {
                end = Math.max(end, curLine.end);
            } else {
                // 겹치는 선분이 아닐 경우엔 현재를 기준으로 선분을 더해주고
                // start와 end값을 갱신한다.
                sum += end - start;
                start = curLine.start;
                end = curLine.end;
            }
        }

        // 마지막 선분의 길이를 추가한다.
        sum += end - start;
        System.out.println(sum);


    }

    static class Line implements Comparable<Line> {
        int start;
        int end;

        Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line other) {

            if (this.start != other.start) {
                return this.start - other.start;
            } else {
                return this.end - other.end;
            }

        }
    }

}

