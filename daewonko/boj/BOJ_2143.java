package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 백준 두 배열의 합
public class BOJ_2143 {
    static int n,m;
    static Long t;

    static Long [] A, B;
    static List<Long> arr = new ArrayList<>();
    static List<Long> brr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Long.parseLong(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new Long[n];
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());

        B = new Long[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                arr.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            long sum =0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                brr.add(sum);
            }
        }

        // B관련 연속누적합 배열을 오름차순으로 정렬
        Collections.sort(brr);

        int count = 0;
        for (long x : arr) {
            count += upperBound(brr, t - x) - lowerBound(brr, t - x);
        }
        System.out.println(count);




    }

    // target보다 커지는 처음의 수를 찾는다.
    static int upperBound(List<Long> list, long target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) > target) right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    static int lowerBound(List<Long> list, long target) {
        int left = 0;
        int right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
