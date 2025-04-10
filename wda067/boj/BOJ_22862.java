import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 가장 긴 짝수 연속한 부분 수열 (large) / 골드5
https://www.acmicpc.net/problem/22862
 */
public class BOJ_22862 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int oddCount = 0;
        int maxLength = 0;

        while (right < N) {
            if (arr[right] % 2 != 0) {
                oddCount++;
            }

            while (oddCount > K) {
                if (arr[left] % 2 != 0) {
                    oddCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1 - oddCount);
            right++;
        }

        System.out.println(maxLength);
    }
}
