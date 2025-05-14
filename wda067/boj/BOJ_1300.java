import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
백준 / K번째 수 / 골드1
https://www.acmicpc.net/problem/1300
 */
public class BOJ_1300 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //100,000
        int k = Integer.parseInt(br.readLine());  //1,000,000,000

        long s = 1;
        long e = (long) N * N;
        long answer = 0;

        while (s <= e) {
            long m = (s + e) / 2;
            long count = 0;

            //중앙값보다 작은 수 카운트
            for (int i = 1; i <= N; i++) {
                count += Math.min(m / i, N);  //i행에서 중앙값보다 작은 수의 개수
            }

            if (count < k) {
                s = m + 1;
            } else {
                answer = m;
                e = m - 1;
            }
        }

        System.out.println(answer);
    }
}