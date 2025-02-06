package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_1722 {
    static int n;
    static long[] factorial;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        factorial = new long[n + 1];
        visited = new boolean[n + 1];

        computeFactorial(); // 팩토리얼 미리 계산

        if (k == 1) {
            // k번째 순열 찾기
            long pos = Long.parseLong(st.nextToken());
            findKthPermutation(pos);
        } else if (k == 2) {
            // 주어진 순열이 몇 번째인지 찾기
            List<Integer> target = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                target.add(Integer.parseInt(st.nextToken()));
            }
            findPermutationIndex(target);
        }
    }

    // 팩토리얼 미리 계산
    static void computeFactorial() {
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    // k번째 순열 찾기 (팩토리얼 사용)
    static void findKthPermutation(long k) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        k--; // 1-based index -> 0-based index
        StringBuilder sb = new StringBuilder();

        for (int i = n; i > 0; i--) {
            int index = (int) (k / factorial[i - 1]); // 현재 자리 숫자 결정
            sb.append(numbers.get(index)).append(" ");
            numbers.remove(index);
            k %= factorial[i - 1]; // 남은 k값 조정
        }

        System.out.println(sb);
    }

    // 주어진 순열이 몇 번째인지 찾기 (팩토리얼 사용)
    static void findPermutationIndex(List<Integer> target) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        long index = 1; // 1-based index
        for (int i = 0; i < n; i++) {
            int num = target.get(i);
            int pos = numbers.indexOf(num); // 현재 숫자가 numbers 리스트에서 몇 번째인지 찾기
            index += pos * factorial[n - 1 - i]; // 남은 자리 경우의 수 반영
            numbers.remove(pos);
        }

        System.out.println(index);
    }
}
