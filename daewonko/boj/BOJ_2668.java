package daewonko.boj;

import java.util.*;


public class BOJ_2668 {
    static List<Integer> selected = new ArrayList<>();
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        arr = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            boolean[] temp = new boolean[n + 1];
            if (dfs(i, i, temp)) {
                selected.add(i);
            }
        }

        Collections.sort(selected);
        System.out.println(selected.size());
        for (int num : selected) {
            System.out.println(num);
        }
    }

    static boolean dfs(int start, int current, boolean[] temp) {
        if (temp[current]) return current == start;
        temp[current] = true;
        return dfs(start, arr[current], temp);
    }
}
