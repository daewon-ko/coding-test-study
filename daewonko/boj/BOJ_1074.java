package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 Z
public class BOJ_1074 {
    static int n,r,c;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = (int)Math.pow(2, n);
        solve(0, 0, size);
    }

    public static void solve(int y, int x, int size) {
        if (size == 1) {
            System.out.println(result);
            return;
        }

        int halfSize = size / 2;

        // 1사분면
        if (r < y + halfSize && c < x + halfSize) {
            solve(y, x, halfSize);
        }
        // 2사분면
        else if (r < y + halfSize && c >= x + halfSize) {
            result += halfSize * halfSize * 1;
            solve(y, x + halfSize, halfSize);
        }
        // 3사분면
        else if (r >= y + halfSize && c < x + halfSize) {
            result += halfSize * halfSize * 2;
            solve(y + halfSize, x, halfSize);
        }
        // 4사분면
        else {
            result += halfSize * halfSize * 3;
            solve(y + halfSize, x + halfSize, halfSize);
        }
    }

}
