package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_3151 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long count = 0;

        for(int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while(left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if(sum == 0) {
                    if(arr[left] == arr[right]) {
                        int len = right - left + 1;
                        count += (long) len * (len - 1) / 2;
                        break;
                    } else {
                        int leftVal = arr[left];
                        int rightVal = arr[right];
                        int leftCount = 0;
                        int rightCount = 0;

                        while(left < right && arr[left] == leftVal) {
                            leftCount++;
                            left++;
                        }
                        while(right >= left && arr[right] == rightVal) {
                            rightCount++;
                            right--;
                        }

                        count += (long) leftCount * rightCount;
                    }
                } else if(sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}
