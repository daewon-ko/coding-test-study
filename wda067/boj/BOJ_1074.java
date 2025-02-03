import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / Z / 골드5
https://www.acmicpc.net/problem/1074
 */
public class BOJ_1074 {

    private static int count, r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int len = (int) Math.pow(2, N);

        recur(len, 0, 0);
    }

    private static void recur(int len, int curR, int curC) {
        if (len == 1) {
            System.out.println(count);
            return;
        }

        int newLen = len / 2;

        if (r < curR + newLen && c < curC + newLen) {
            recur(newLen, curR, curC);
        } else if (r < curR + newLen && c >= curC + newLen) {
            count += newLen * newLen;
            recur(newLen, curR, curC + newLen);
        } else if (r >= curR + newLen && c < curC + newLen) {
            count += 2 * newLen * newLen;
            recur(newLen, curR + newLen, curC);
        } else {
            count += 3 * newLen * newLen;
            recur(newLen, curR + newLen, curC + newLen);
        }
    }
}
