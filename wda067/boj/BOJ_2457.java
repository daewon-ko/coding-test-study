import static java.util.Comparator.comparingInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 / 공주님의 정원 / 골드3
https://www.acmicpc.net/problem/2457
 */
public class BOJ_2457 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        //피는 날짜순으로 오름차순, 피는 날짜가 같으면 지는 날짜순으로 내림차순
        PriorityQueue<Flower> flowers = new PriorityQueue<>(comparingInt((Flower f) -> f.start)
                .thenComparing(comparingInt((Flower f) -> f.end)
                        .reversed()));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int start = a * 100 + b;
            int end = c * 100 + d;
            flowers.add(new Flower(start, end));
        }

        int curDate = 301;
        int count = 0;

        while (curDate <= 1130) {  //11일 30일까지 반복
            int maxDate = curDate;  //마지막 꽃이 지는 날

            while (!flowers.isEmpty()) {
                if (curDate < flowers.peek().start) {  //curDate 이후에 꽃이 필 경우 스킵
                    break;
                }

                Flower flower = flowers.poll();
                maxDate = Math.max(maxDate, flower.end);  //뽑힌 꽃으로 지는 날 연장
            }

            if (maxDate == curDate) {  //연장되지 않았다면 꽃이 선택되지 않은 기간이 존재한다는 뜻
                System.out.println(0);
                return;
            }

            curDate = maxDate;
            count++;  //꽃 카운트
        }

        System.out.println(count);
    }

    static class Flower {
        private int start, end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}