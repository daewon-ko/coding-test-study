package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
<<<<<<< HEAD
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
=======
import java.util.StringTokenizer;

public class BOJ_2143 {


    //백준 두 배열의 합
    static Long t;
    static int n, m;
    static Long[] arr;
    static Long[] brr;

>>>>>>> 1148dd0 (feat : BOJ / 2143 / 골드3)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Long.parseLong(br.readLine());
        n = Integer.parseInt(br.readLine());
<<<<<<< HEAD
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


=======
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            brr[i] = Long.parseLong(st.nextToken());
        }

        backtracking(0L);
>>>>>>> 1148dd0 (feat : BOJ / 2143 / 골드3)


    }

<<<<<<< HEAD
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
=======
    // sum이 t여야한다.
    // t는 -10억에서 +10억까지이다.
    // 일반적인 백트래킹은 인덱스와 값이 일치하는 양의정수의 값을 구하는 문제인데,
    // 해당문제는 t가 음의 정수가 될 수도 있고 심지어 그 값의 범위가 크다.
    // 따라서 visited 배열을 이용할 수 없을 것으로 보인다.
    public static void backtracking(Long sum) {
        if (sum == t) {
            //A와 B의 연속된 부배열이 존재하는지 확인
            // 한 개 이상의 원소를 지닌 연속된 부분집합을 더했을때 ,
            // 각 arr과 brr에서 더해서 만들 수 있는지를 확인하고 있다면 cnt ++
            // O(N)
            // 구하고자 하는 부분집합의 값이 무엇인지 알 수가 없지 않을까?


            // O(M)

        }


    }
}


>>>>>>> 1148dd0 (feat : BOJ / 2143 / 골드3)
