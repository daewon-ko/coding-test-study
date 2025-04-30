import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 저울 / 골드2
https://www.acmicpc.net/problem/2437
 */
public class BOJ_2437 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] weights = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights);

        int sum = 0;
        for (int weight : weights) {
            if (weight > sum + 1) {
                break;
            }
            sum += weight;
        }

        System.out.println(sum + 1);
    }
}

