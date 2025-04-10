package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_22862 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] arr = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) % 2 == 0;
        }

        int left = 0;
        int right = 0;
        int odds = K;
        int max = 0;

        while (right < N) {
            // 1) right를 최대로 두기
            while (right < N && odds >= 0) {
                if (!arr[right]) {
                    odds--;
                }
                right++;
            }
            
            // 2) left 한 칸 전진
            max = Math.max(max, right - left - K + odds);
            if (!arr[left]) {
                odds++;
            }
            left++;
        }

        System.out.println(max);
    }
}
