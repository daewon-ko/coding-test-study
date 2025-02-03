package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {
    static long[] tree;
    static int leafStart;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));  // 2 ^ h >= N -> 2 * 2 ^ h 가 세그먼트 크기
        leafStart = 1 << h;  // 리프 노드의 시작 인덱스 (1을 h비트 만큼 왼쪽으로 이동 -> 1 * 2 ^ h
        int size = 2 * leafStart;
        tree = new long[size];

        // 리프 노드에 입력 값 저장
        for (int i = 0; i < N; i++) {
            tree[leafStart + i] = Long.parseLong(br.readLine());
        }

        // 자식 노드 2개 합 = 부모 노드
        for (int i = leafStart - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(b, c);
            } else {
                sb.append(sum(b, (int) c)).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void update(int index, long value) {
        index = leafStart + index - 1;
        long diff = value - tree[index];
        while (index > 0) {
            tree[index] += diff;
            index /= 2;
        }
    }

    static long sum(int start, int end) {
        start = leafStart + start - 1;
        end = leafStart + end - 1;
        long sum = 0;

        while (start <= end) {
            if (start % 2 == 1) {
                sum += tree[start++];
            }
            if (end % 2 == 0){
                sum += tree[end--];
            }
            start /= 2;
            end /= 2;
        }
        return sum;
    }
}