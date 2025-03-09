import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 / 세 용액 / 골드3
https://www.acmicpc.net/problem/2473
 */
public class BOJ_2473 {

    private static int N;
    private static long[] features, result;
    private static long min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  //3 ~ 5,000
        features = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            features[i] = Long.parseLong(st.nextToken());
        }

        min = Long.MAX_VALUE;
        result = new long[3];
        Arrays.sort(features);

        //투 포인터
        for (int i = 0; i < N - 2; i++) {
            if (twoPointer(i)) {
                return;
            }
        }

        //이진 탐색
        //for (int i = 0; i < N - 2; i++) {
        //    for (int j = i + 1; j < N - 1; j++) {
        //        binarySearch(i, j);
        //    }
        //}

        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    private static boolean twoPointer(int i) {
        int s = i + 1;
        int e = N - 1;

        while (s < e) {
            long sum = features[i] + features[s] + features[e];

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                result[0] = features[i];
                result[1] = features[s];
                result[2] = features[e];
            }

            if (sum < 0) {
                s++;
            } else if (sum > 0) {
                e--;
            } else {
                System.out.println(result[0] + " " + result[1] + " " + result[2]);
                return true;
            }
        }
        return false;
    }

    private static void binarySearch(int i, int j) {
        long sum = features[i] + features[j];

        int s = j + 1;
        int e = N - 1;

        while (s <= e) {
            int m = (s + e) / 2;
            long total = sum + features[m];

            if (Math.abs(total) < min) {
                min = Math.abs(total);
                result[0] = features[i];
                result[1] = features[j];
                result[2] = features[m];
            }

            if (total <= 0) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
    }
}
