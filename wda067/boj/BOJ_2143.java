import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 두 배열의 합 / 골드3
https://www.acmicpc.net/problem/2143
 */
public class BOJ_2143 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        //A배열 초기화
        int[] A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        //B배열 초기화
        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        //A배열의 구간합
        int[] sumA = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sumA[i] = sumA[i - 1] + A[i];
        }

        //B배열의 구간합
        int[] sumB = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            sumB[i] = sumB[i - 1] + B[i];
        }

        //A 구간합의 모든 경우의 수
        List<Integer> listA = new ArrayList<>();
        for (int i = N; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                int x = sumA[i] - sumA[j - 1];
                listA.add(x);
            }
        }

        //B 구간합의 모든 경우의 수
        List<Integer> listB = new ArrayList<>();
        for (int i = M; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                int y = sumB[i] - sumB[j - 1];
                listB.add(y);
            }
        }

        Collections.sort(listA);  //오름차순
        Collections.sort(listB, Comparator.reverseOrder());  //내림차순

        long count = 0;

        //투 포인터
        int s = 0;
        int e = 0;
        while (s < listA.size() && e < listB.size()) {
            int a = listA.get(s);  //A 부 배열의 합
            int b = listB.get(e);  //B 부 배열의 합
            int sum = a + b;  //부 배열의 합

            if (sum == T) {
                //동일한 부 배열 개수 카운트
                long countA = 0;
                while (s < listA.size() && listA.get(s) == a) {
                    countA++;
                    s++;
                }

                long countB = 0;
                while (e < listB.size() && listB.get(e) == b) {
                    countB++;
                    e++;
                }

                count += countA * countB;
            } else if (sum < T) {  //합이 작으면 A에서 증가
                s++;
            } else {  //합이 크면 B에서 감소(내림차순)
                e++;
            }
        }

        System.out.println(count);
    }
}
