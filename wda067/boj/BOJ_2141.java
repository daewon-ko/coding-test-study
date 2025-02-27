import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
백준 / 우체국 / 골드4
https://www.acmicpc.net/problem/2141
 */
public class BOJ_2141 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //1 ~ 100,000
        long[][] villages = new long[N][2];

        long total = 0;  //총 인구

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());  //좌표
            long a = Long.parseLong(st.nextToken());  //인구

            villages[i][0] = x;
            villages[i][1] = a;
            total += a;
        }

        Arrays.sort(villages, Comparator.comparingLong((long[] a) -> a[0]));  //좌표를 기준으로 정렬

        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += villages[i][1];
            if (sum >= (total + 1) / 2) {  //중앙값에 가까운 마을 찾기
                System.out.println(villages[i][0]);
                return;
            }
        }
    }
}
