package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042
{
    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //수의 개수
        int M = Integer.parseInt(st.nextToken()); //수의 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); //구간의 합을 구하는 횟수

        arr = new long[N + 1];
        tree = new long[N * 4];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, diff);
            } else if (a == 2) {
                sb.append(sum(1, N, 1, b, (int) c)).append("\n");
            }
        }
        System.out.print(sb);
    }

    //세그먼트 트리 초기화
    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    //구간 합 구하기
    static long sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    //값 업데이트
    static void update(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) {
            return;
        }
        tree[node] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            update(start, mid, node * 2, index, diff);
            update(mid + 1, end, node * 2 + 1, index, diff);
        }
    }
}
