package yeonjy.boj;

import java.io.*;
import java.util.*;

public class BOJ_14002 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = calculate(arr);
        int idxOfMaxValue = getIdxOfMaxValue(dp);
        System.out.println(dp[idxOfMaxValue] + 1);

        List<Integer> result = getPart(idxOfMaxValue, dp);
        for (int i = result.size() - 1; i >= 0; i--) {
            bw.write(result.get(i) + " ");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int[] calculate(int[] arr) {
        int[] dp = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j < i; j++) {
                if (arr[i - 1] > arr[j - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    static int getIdxOfMaxValue(int[] dp) {
        int max = Arrays.stream(dp).max().getAsInt();
        int res = 0;
        int idx = dp.length - 1;
        while(res == 0) {
            if (dp[idx] == max) {
                res = idx;
            }
            idx--;
        }
        return res;
    }

    static List<Integer> getPart(int idx, int[] dp) {
        List<Integer> res = new ArrayList<>();
        res.add(arr[idx - 1]);
        for (int i = idx; i > 0; i--) {
            if (dp[i] == dp[idx] - 1 && arr[i - 1] < arr[idx - 1]) {
                res.add(arr[i - 1]);
                idx = i;
            }
        }
        return res;
    }
}
