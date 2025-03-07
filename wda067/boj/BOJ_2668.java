import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ_2668 {

    private static int[] numbers;
    private static boolean[] visited;
    private static boolean[] inCycle;
    private static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        numbers = new int[n + 1];
        visited = new boolean[n + 1];
        inCycle = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            Arrays.fill(visited, false);
            dfs(i, i);
        }

        Collections.sort(result);
        System.out.println(result.size());
        for (int num : result) {
            System.out.println(num);
        }
    }

    private static void dfs(int start, int current) {
        if (!visited[current]) {
            visited[current] = true;
            dfs(start, numbers[current]);
        } else if (start == current) {
            for (int i = 1; i < visited.length; i++) {
                if (visited[i] && !inCycle[i]) {
                    inCycle[i] = true;
                    result.add(i);
                }
            }
        }
    }
}
