package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 카드 바꾸기
public class BOJ_25401 {
    static int n;
    static int [] arr;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        System.out.println(getMinimunChanges(arr, n));



    }

    public static int getMinimunChanges(int[] arr, int n) {
        if (n == 1) {
            return 0;
        }


        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {

                int diff = arr[j] - arr[i];
                int len = j - i;
                if (diff % len != 0) {
                    continue;
                }

                int d = diff / len;

                int a1 = arr[i] - (i - 1) * d;


                int change = 0;
                for (int k = 1; k <= n; k++) {
                    int expected = a1 + (k - 1) * d;
                    if (arr[k] != expected) {
                        change++;
                    }
                }

                answer = Math.min(answer, change);
            }
        }

        return answer;

    }
}
