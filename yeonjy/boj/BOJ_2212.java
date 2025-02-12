package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2212 {
    static int N;
    static int K;
    static List<Integer> list;
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        list = new ArrayList<>(set);
        Collections.sort(list);
        N = list.size();

        if (K >= N) {
            System.out.println(0);
            return;
        }

        List<Integer> diffs = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            diffs.add(list.get(i) - list.get(i - 1));
        }

        diffs.sort(Collections.reverseOrder());

        int cost = 0;
        // 가장 큰 (K-1)개의 차이를 제거하면 K개의 그룹을 만들 수 있음
        for (int i = K - 1; i < diffs.size(); i++) {
            cost += diffs.get(i);
        }
        System.out.println(cost);
    }
}
