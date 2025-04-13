import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
프로그래머스 / 추석 트래픽 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/17676
 */
public class PGS_17676 {

    public int solution(String[] lines) throws Exception {
        List<long[]> times = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

        for (String line : lines) {
            String[] parts = line.split(" ");
            Date endDate = sdf.parse(parts[1]);
            long end = endDate.getTime();
            double processTime = Double.parseDouble(parts[2].replace("s", ""));
            long start = end - (long) (processTime * 1000) + 1;  //시작시간 = 끝 - 처리시간 + 1ms

            times.add(new long[]{start, end});
        }

        int max = 0;
        for (int i = 0; i < times.size(); i++) {
            //현재 로그가 끝나는 시간 기준 1초 구간
            long startRange = times.get(i)[1];
            long endRange = startRange + 1000 - 1;

            int count = 0;
            for (long[] time : times) {
                long start = time[0];
                long end = time[1];

                if (start <= endRange && end >= startRange) {
                    count++;
                }
            }

            max = Math.max(max, count);
        }

        return max;
    }
}

