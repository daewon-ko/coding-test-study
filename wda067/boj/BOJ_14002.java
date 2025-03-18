import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
백준 / 가장 긴 증가하는 부분 수열 4 / 골드4
https://www.acmicpc.net/problem/14002
 */
public class BOJ_14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        int[] trace = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            trace[i] = -1;
        }

        int maxLen = 1, maxIdx = 0;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    trace[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        LinkedList<Integer> lis = new LinkedList<>();
        while (maxIdx != -1) {
            lis.addFirst(arr[maxIdx]);
            maxIdx = trace[maxIdx];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxLen).append("\n");
        for (int num : lis) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }
}

