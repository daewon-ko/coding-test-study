import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 나머지 합 / 골드3
https://www.acmicpc.net/problem/10986
 */
public class BOJ_10986 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  //수의 개수
        int M = Integer.parseInt(st.nextToken());  //제수 (divisor)
        st = new StringTokenizer(br.readLine());

        long[] sum = new long[N];  //구간 합 배열
        sum[0] = Long.parseLong(st.nextToken());
        for (int i = 1; i < N; i++) {
            //S[i] = S[i - 1] + A[i]
            sum[i] = sum[i - 1] + Long.parseLong(st.nextToken());
        }

        long answer = 0;
        long[] count = new long[M];  //같은 나머지 카운트
        for (int i = 0; i < N; i++) {
            //합 배열을 나눈 나머지가 0이면 정답++
            int remainder = (int) (sum[i] % M);
            if (remainder == 0) {
                answer++;
            }
            //나머지가 0이 아닐 때 카운트
            count[remainder]++;
        }

        for (int i = 0; i < M; i++) {
            //같은 나머지의 개수에서 2가지를 뽑는 경우의 수를 정답에 더한다.
            if (count[i] > 1) {
                answer += count[i] * (count[i] - 1) / 2;
            }
        }

        System.out.println(answer);
    }
}
