package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12970 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int aSize = 1;
        int bSize = N - 1;
        while (aSize * bSize < K) {
            if (bSize < 0) {
                aSize = -1;
                break;
            }
            aSize++;
            bSize--;
        }
        bSize = N - aSize;
        String[] result = new String[N];
        if (aSize != -1 && K != 0) {
            for (int i = 0; i < aSize - 1; i++) {
                result[i] = "A";
            }
            for (int i = aSize - 1; i < N; i++) {
                result[i] = "B";
            }

            int idx = K - (aSize - 1) * bSize;
            result[N - 1 - idx] = "A";
        } else if (K == 0) {
            Arrays.fill(result, "B");
        }
        if (aSize == -1) {
            System.out.println(-1);
            return;
        }
        System.out.println(String.join("", result));
    }
}
