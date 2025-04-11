package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] sumArr = new long[N];
        long[] remArr = new long[M];
        long cnt = 0;

        //수 입력받기 (arr[1] 행만 채움)
        st = new StringTokenizer(br.readLine());
        sumArr[0] = Long.parseLong(st.nextToken());
        for (int i = 1; i < N; i++) {
            sumArr[i] = Long.parseLong(st.nextToken()) + sumArr[i-1];
        }

        for (int i = 0; i < N; i++) {
            int rem = (int) (sumArr[i] % M);
            if (rem == 0) cnt++;
            remArr[rem]++;
        }

        for (int i = 0; i < M; i++) {
            if (remArr[i] > 1) cnt += remArr[i] * (remArr[i] - 1) / 2;
        }

        System.out.println(cnt);
    }
}
