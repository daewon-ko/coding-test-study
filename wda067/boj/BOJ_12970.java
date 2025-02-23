import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / AB / 골드4
https://www.acmicpc.net/problem/12970
 */
public class BOJ_12970 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  //2~50
        int K = Integer.parseInt(st.nextToken());

        char[] S = new char[N];

        if (K == 0) {  //K가 0이면 A로만 구성됨
            Arrays.fill(S, 'A');
            System.out.println(new String(S));
            return;
        }

        boolean flag = false;
        Arrays.fill(S, 'B');
        int a = 0;  //A의 개수
        int b = N;  //B의 개수

        //(A, B) 쌍의 최대 개수는 a * b
        while (a <= N) {
            if (a * b < K) {  //a * b = K인 a, b를 찾는다.
                a++;
                b--;
                continue;
            }

            //a-1개 만큼 A를 순서대로 채운다.
            for (int i = 0; i < a - 1; i++) {
                S[i] = 'A';
            }

            int cur = (a - 1) * b;  //현재 (A, B) 쌍의 개수
            int remaining = K - cur;  //필요한 (A, B) 쌍의 개수
            S[(N - 1) - remaining] = 'A';  //끝에 A를 두고 왼쪽으로 한 칸씩 이동할 때마다 쌍의 개수가 1개씩 늘어남
            flag = true;
            break;
        }

        if (flag) {
            System.out.println(new String(S));
        } else {
            System.out.println(-1);
        }
    }
}
