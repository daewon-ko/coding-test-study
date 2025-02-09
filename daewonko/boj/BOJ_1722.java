package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;

public class BOJ_1722 {

    static long[] fact;
    static boolean[] used;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        fact = new long[N + 1];
        used = new boolean[N + 1];
        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i;
        }

        st = new StringTokenizer(br.readLine());
        int queryType = Integer.parseInt(st.nextToken());

        if (queryType == 1) {
            long k = Long.parseLong(st.nextToken());
            findKthPermutation(k);
        } else {
            int[] perm = new int[N];
            for (int i = 0; i < N; i++) {
                perm[i] = Integer.parseInt(st.nextToken());
            }
            findPermutationIndex(perm);
        }
    }

    static void findKthPermutation(long k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!used[j]) {
                    if (fact[N - 1 - i] < k) {
                        k -= fact[N - 1 - i];
                    } else {
                        sb.append(j).append(" ");
                        used[j] = true;
                        break;
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }

    static void findPermutationIndex(int[] perm) {
        long index = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < perm[i]; j++) {
                if (!used[j]) index += fact[N - 1 - i];
            }
            used[perm[i]] = true;
        }
        System.out.println(index);
    }
}


class BOJ_1722_FAIL {
    static int n;
    static int k;
    static int[] arr;
    static boolean[] visited;
    static int cnt;
    static StringBuilder findArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        visited = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        if (k == 1) {
            int order = Integer.parseInt(st.nextToken());
            solve1(1, order);

        } else if (k == 2) {
            findArray = new StringBuilder();
            for (int i = 0; i < n; i++) {
                findArray.append(Integer.parseInt(st.nextToken()));
                findArray.append(" ");
            }

            solve2(1);

        }


    }

    public static void solve1(int number, int order) {
        if (number == n + 1) {
            cnt++;

            // 출력
            if (cnt == order) {

                for (int i = 1; i <= n; i++) {
                    System.out.printf(arr[i] + " ");

                }
            }


            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[number] = i;
                solve1(number + 1, order);
                visited[i] = false;
            }
        }


    }

    public static void solve2(int number) {
        if (number == n + 1) {
            cnt++;

            // 비교

            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= n; i++) {
                sb.append(arr[i]).append(" ");
            }

            if (sb.toString().equals(findArray.toString())) {
                System.out.println(cnt);
                System.exit(0);
            }


            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[number] = i;
                solve2(number + 1);
                visited[i] = false;
            }
        }
    }
}
