package yeonjy.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2293 {
    static long[] cnt;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        cnt = new long[k+1];

        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        int[] sortedNums = Arrays.stream(nums).sorted().toArray();

        for(int num : sortedNums) {
            if(num <= k) {
                dp(num);
            }
        }

        System.out.println(cnt[k]);
        br.close();
    }
    static void dp(int num) {
        cnt[num]++;
        for(int i = num; i <= k; i++) {
            cnt[i] = cnt[i] + cnt[i-num];
        }
    }
}
