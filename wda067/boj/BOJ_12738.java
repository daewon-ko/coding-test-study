import static java.util.Collections.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 가장 긴 증가하는 부분 수열 3 / 골드2
https://www.acmicpc.net/problem/12738
 */
public class BOJ_12738 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //수열의 크기가 최대 100만이므로 이중 for문은 불가
        //int[] dp = new int[N];
        //
        //for (int i = 0; i < N; i++) {
        //    dp[i] = 1;
        //    for (int j = 0; j < i; j++) {
        //        if (arr[i] > arr[j]) {
        //            dp[i] = Math.max(dp[i], dp[j] + 1);
        //        }
        //    }
        //}
        //
        //System.out.println(dp[N - 1]);

        List<Integer> lis = new ArrayList<>();
        for (int num : arr) {  //이진 탐색으로 LIS 길이 결정
            int m = binarySearch(lis, num);

            if (m < 0) {  //m이 음수일 때는 양수로 변환
                m = -(m + 1);
            }

            if (m == lis.size()) {
                lis.add(num);
            } else {
                lis.set(m, num);
            }
        }

        System.out.println(lis.size());
    }
}
