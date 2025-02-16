import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 센서 / 골드5
https://www.acmicpc.net/problem/2212
 */
public class BOJ_2212 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //1 ~ 10,000
        int K = Integer.parseInt(br.readLine());  //1 ~ 1,000
        int[] sensors = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensors);

        int[] gaps = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            gaps[i] = sensors[i + 1] - sensors[i];
        }

        Arrays.sort(gaps);

        int min = 0;
        for (int i = 0; i < N - K; i++) {
            min += gaps[i];
        }

        System.out.println(min);
    }
}
