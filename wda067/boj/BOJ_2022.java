import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 / 사다리 / 골드4
https://www.acmicpc.net/problem/2022
 */
public class BOJ_2022 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());

        double low = 0;
        double high = Math.min(x, y);

        for (int i = 0; i < 100; i++) {
            double mid = (low + high) / 2.0;

            double h1 = Math.sqrt(x * x - mid * mid);
            double h2 = Math.sqrt(y * y - mid * mid);
            double h = (h1 * h2) / (h1 + h2);

            if (h < c) {
                high = mid;
            } else {
                low = mid;
            }
        }

        System.out.printf("%.3f", low);
    }
}
