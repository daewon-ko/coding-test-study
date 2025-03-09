package daewonko.boj;
import java.io.*;
import java.util.*;

public class BOJ_2473 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);  // 1. 정렬

        long closestSum = Long.MAX_VALUE;
        long a = 0, b = 0, c = 0;

        // 2. 첫 번째 용액을 고정
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];

                // 3. 0에 가까운 값 갱신
                if (Math.abs(sum) < Math.abs(closestSum)) {
                    closestSum = sum;
                    a = arr[i];
                    b = arr[left];
                    c = arr[right];
                }

                // 4. 포인터 이동
                if (sum < 0) {
                    left++;  // sum이 0보다 작으면 왼쪽 포인터를 이동하여 값을 증가
                } else {
                    right--; // sum이 0보다 크면 오른쪽 포인터를 이동하여 값을 감소
                }
            }
        }

        System.out.println(a + " " + b + " " + c);
    }
}
