package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2629 {
    static int N;
    static int[] chu;
    static int num;
    static int[] gusul;
    static boolean[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        chu = new int[N];
        for (int i = 0; i < N; i++) {
            chu[i] = Integer.parseInt(st.nextToken());
        }
        num = Integer.parseInt(br.readLine());
        gusul = new int[num];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            gusul[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        cache = new boolean[N + 1][40001];
        dp(0, 0);
        for (int i = 0; i < num; i++) {
            String result = "N";
            for (int j = 0; j < N + 1; j++) {
                if (cache[j][gusul[i]]) {
                    result = "Y";
                    break;
                }
            }
            sb.append(result).append(" ");
        }
        System.out.println(sb);

    }

    private static void dp(int n, int weight) {
        if (weight < 0 || weight > 40000) {
            return;
        }
        if (cache[n][weight]) {
            return;
        }
        cache[n][weight] = true;
        if (n == N) {
            return;
        }
        dp(n + 1, weight + chu[n]);
        dp(n + 1, weight);
        dp(n + 1, (weight < chu[n]) ? chu[n] - weight : weight - chu[n]);
    }
}
