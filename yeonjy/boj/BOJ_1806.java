package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;

        while (right <= N && left >= 0) {
            if (sum >= S) {
                ans = Math.min(ans, right - left);
                sum -= arr[left++];
            } else {
                sum += arr[right++];
            }
        }
        
        if (ans == Integer.MAX_VALUE) {
            ans = 0;
        }
        System.out.println(ans);
    }
}
