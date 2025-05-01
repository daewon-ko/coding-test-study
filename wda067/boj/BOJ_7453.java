import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 합이 0인 네 정수 / 골드2
https://www.acmicpc.net/problem/7453
 */
public class BOJ_7453 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            A[i] = a;
            B[i] = b;
            C[i] = c;
            D[i] = d;
        }

        //합 배열
        int[] AB = new int[N * N];
        int[] CD = new int[N * N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx++] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        long answer = 0;
        int s = 0;
        int e = N * N - 1;

        while (s < N * N && e >= 0) {  //투 포인터
            int sum = AB[s] + CD[e];

            if (sum < 0) {
                s++;
            } else if (sum > 0) {
                e--;
            } else {  //조건에 만족하는 경우
                long countAB = 0;
                int sumAB = AB[s];
                while (s < N * N && sumAB == AB[s]) {  //동일한 합의 경우의 수
                    s++;
                    countAB++;
                }

                long countCD = 0;
                int sumCD = CD[e];
                while (e >= 0 && sumCD == CD[e]) {
                    e--;
                    countCD++;
                }

                answer += countAB * countCD;  //AB, CD 각 경우의 수 계산
            }
        }

        System.out.println(answer);
    }
}
