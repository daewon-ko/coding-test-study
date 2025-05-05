import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 합이 0 / 골드4
https://www.acmicpc.net/problem/3151
 */
public class BOJ_3151 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long count = 0;

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == 0) {
                    if (arr[left] == arr[right]) {
                        int n = right - left + 1;
                        count += (long) n * (n - 1) / 2;
                        break;
                    } else {
                        int lCount = 1;
                        int rCount = 1;
                        while (left + 1 < right && arr[left] == arr[left + 1]) {
                            lCount++;
                            left++;
                        }
                        while (right - 1 > left && arr[right] == arr[right - 1]) {
                            rCount++;
                            right--;
                        }
                        count += (long) lCount * rCount;
                        left++;
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}
