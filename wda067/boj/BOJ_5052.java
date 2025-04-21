import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
백준 / 전화번호 목록 / 골드4
https://www.acmicpc.net/problem/5052
 */
public class BOJ_5052 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());  //50

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());  //10,000
            String[] arr = new String[N];

            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
            }

            Arrays.sort(arr);

            boolean flag = true;

            for (int i = 0; i < N - 1; i++) {
                String cur = arr[i];
                String next = arr[i + 1];

                if (next.startsWith(cur)) {  //cur이 접두어일 경우
                    flag = false;
                    break;
                }
            }

            if (flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
