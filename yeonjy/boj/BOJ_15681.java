package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15681 {
    static List<Integer>[] arr;
    static boolean[] isVisited;
    static int[] subs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        arr = new List[N + 1];
        isVisited = new boolean[N + 1];
        subs = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u].add(v);
            arr[v].add(u);
        }

        dfs(R);

        for (int i = 0; i < Q; i++) {
            int test = Integer.parseInt(br.readLine());
            sb.append(subs[test]).append("\n");
        }
        System.out.println(sb);
    }

    static int dfs(int now) {
        isVisited[now] = true;
        int count = 1;

        for (int next : arr[now]) {
            if (!isVisited[next]) {
                count += dfs(next);
            }
        }
        subs[now] = count;
        return count;
    }
}
