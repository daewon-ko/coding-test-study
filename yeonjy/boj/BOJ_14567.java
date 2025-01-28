package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] terms = new int[N + 1];
        List<Integer>[] pres = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            pres[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());

            pres[post].add(pre);
        }

        for (int i = 1; i <= N; i++) {
            if (pres[i].isEmpty()) {
                terms[i] = 1;
            } else {
                int max = 1;
                for (int pre : pres[i]) {
                    max = Math.max(max, terms[pre] + 1);
                }
                terms[i] = max;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(terms[i] + " ");
        }
        System.out.println(sb);
    }
}
