package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 백준 세 수의 합
public class BOJ_2295 {
    static int n;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                list.add(arr[i] + arr[j]);
            }
        }

        Collections.sort(list);

        // d는 최적화를 위해 뒤에서 부터 출발
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int d = arr[i];
                int c = arr[j];

                int target = d - c;

                if (binarySearch(list, target)) {
                    MAX = Math.max(MAX, d);
                }

            }
        }

        System.out.println(MAX);

    }

    public static boolean binarySearch(List<Integer> list, int target) {
        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (target == list.get(mid)) {
                return true;
            } else if (list.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }

        return false;
    }
}
