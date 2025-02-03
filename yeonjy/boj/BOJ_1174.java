package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1174 {
    static List<Long> list = new ArrayList<>();
    static final int[] nums = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dfs(0, 0);
        Collections.sort(list);
        if (N > list.size()) {
            System.out.println(-1);
            return;
        }
        System.out.println(list.get(N - 1));
    }

    private static void dfs(long num, int index) {
        if (!list.contains(num)) {
            list.add(num);
        }
        if (index >= 10) {
            return;
        }

        // num 1의 자리보다 작은 수를 붙이려면 nums[]의 index가 더 커야 됨. 그래야 더 작은 수가 붙음
        dfs(num * 10 + nums[index], index + 1);
        dfs(num, index + 1);
    }
}
