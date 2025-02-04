import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
백준 / 공주님의 정원 / 골드3
https://www.acmicpc.net/problem/2457
 */
public class BOJ_2457 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Flower[] flowers = new Flower[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());
            flowers[i] = new Flower(startMonth * 100 + startDay, endMonth * 100 + endDay);
        }

        Arrays.sort(flowers, Comparator.comparingInt(f -> f.start));

        int currentDate = 301;
        int count = 0;
        int index = 0;

        while (currentDate <= 1130) {
            int maxEndDate = currentDate;
            while (index < n && flowers[index].start <= currentDate) {
                if (flowers[index].end > maxEndDate) {
                    maxEndDate = flowers[index].end;
                }
                index++;
            }
            if (maxEndDate == currentDate) {
                System.out.println(0);
                return;
            }
            currentDate = maxEndDate;
            count++;
        }

        System.out.println(count);
    }

    static class Flower {
        int start;
        int end;

        Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}