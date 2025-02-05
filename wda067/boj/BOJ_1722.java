import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 순열의 순서 / 골드5
https://www.acmicpc.net/problem/1722
 */
public class BOJ_1722 {

    private static long[] factorial;
    private static boolean[] visited;
    private static int N;
    private static long k, order;
    private static List<Integer> result = new ArrayList<>();
    private static int[] permutation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];

        //팩토리얼 배열 초기화
        factorial = new long[N + 1];
        factorial[0] = 1;
        for (int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());

        if (x == 1) {  //k번째 순열 찾기
            k = Long.parseLong(st.nextToken()) - 1;
            findPermutation(N);
        } else {  //주어진 순열의 순서 찾기
            permutation = new int[N];
            for (int i = 0; i < N; i++) {
                permutation[i] = Integer.parseInt(st.nextToken());
            }
            findOrder(0);
            System.out.println(order + 1);
        }
    }

    //k번째 순열 찾기
    private static void findPermutation(int depth) {
        if (depth == 0) {  //남은 자릿수가 없을 때
            StringBuilder sb = new StringBuilder();
            for (Integer num : result) {
                sb.append(num).append(" ");
            }
            System.out.println(sb);
            return;
        }

        long blockSize = factorial[depth - 1];  //나머지 자리에 가능한 순열의 경우의 수
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                if (k < blockSize) {  //현재 자리에 i를 추가
                    result.add(i);
                    visited[i] = true;
                    findPermutation(depth - 1);
                } else {  //현재 블록을 건너뜀
                    k -= blockSize;
                }
            }
        }
    }

    //주어진 순열이 몇 번째 순열인지 찾기
    private static void findOrder(int depth) {
        if (depth == N) {  //마지막 자리까지 탐색한 경우
            return;
        }

        int cur = permutation[depth];  //현재 자리의 숫자
        long count = 0;

        //현재 자리의 숫자보다 작은 숫자들을 탐색
        for (int i = 1; i < cur; i++) {
            if (!visited[i]) {
                count += factorial[N - depth - 1];  //나머지 자리로 만들어지는 순열의 수
            }
        }

        visited[cur] = true;  //방문 처리
        order += count;
        findOrder(depth + 1);
    }
}
