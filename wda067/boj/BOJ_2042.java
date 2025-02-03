import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 구간 합 구하기 / 골드1
https://www.acmicpc.net/problem/2042
 */
public class BOJ_2042 {

    static long[] arr, tree;
    static int from, to, index;
    static long diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        arr = new long[N + 1];
        tree = new long[4 * N];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        treeInit(1, N, 1);  //트리 초기화

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {  //b번째 수를 c로 변경
                index = b;
                diff = c - arr[index];  //변경하면서 생기는 차이
                arr[index] = c;
                changeValue(1, N, 1);
            } else if (a == 2) {  //b번째 수부터 c번째 수까지의 합
                from = b;
                to = (int) c;
                long sum = getSum(1, N, 1);  //from ~ to 구간합
                sb.append(sum).append("\n");
            }
        }

        System.out.print(sb);
    }

    //세그먼트 트리 초기화
    public static long treeInit(int start, int end, int node) {
        if (start == end) {  //리프 노드
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = treeInit(start, mid, node * 2) + treeInit(mid + 1, end, node * 2 + 1);
    }

    //from~to의 구간합
    public static long getSum(int start, int end, int node) {
        if (to < start || end < from) {  //구간을 벗어난 경우
            return 0;
        }

        if (from <= start && end <= to) {  //구간을 만족한 경우
            return tree[node];
        }

        int mid = (start + end) / 2;
        return getSum(start, mid, node * 2) + getSum(mid + 1, end, node * 2 + 1);
    }

    public static void changeValue(int start, int end, int node) {
        if (index < start || end < index) {
            return;
        }

        tree[node] += diff;  //index의 부모 노드로 올라가면서 diff을 더해준다.
        if (start == end) {  //리프 노드에 도달
            return;
        }

        int mid = (start + end) / 2;
        changeValue(start, mid, node * 2);  //좌측 노드
        changeValue(mid + 1, end, node * 2 + 1);  //우측 노드
    }
}
