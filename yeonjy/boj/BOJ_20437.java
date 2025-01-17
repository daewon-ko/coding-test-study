package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            char[] chars = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());
            if (K == 1) {
                sb.append("1 1\n");
                continue;
            }
            int[] alphas = new int[26];
            for (char now : chars) {
                alphas[now - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = -1;
            for (int i = 0; i < chars.length; i++) {
                if (alphas[chars[i] - 'a'] < K) {
                    continue;
                }
                int count = 1;
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[i] == chars[j]) {
                        count++;
                    }
                    if (count == K) {
                        min = Math.min(min, j - i + 1);
                        max = Math.max(max, j - i + 1);
                        break;
                    }
                }
            }
            if (min == Integer.MAX_VALUE || max == -1) {
                sb.append(-1);
            } else {
                sb.append(min + " " + max);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
