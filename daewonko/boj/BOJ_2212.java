package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 센서
public class BOJ_2212 {
    static int n, k;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] distance = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            distance[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(distance);

        int result = 0;
        for (int i = 0; i < n - k; i++) {
            result += distance[i];
        }
        System.out.println(result);


    }

}
