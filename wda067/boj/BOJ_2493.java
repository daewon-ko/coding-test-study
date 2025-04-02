import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
백준 / 탑 / 골드5
https://www.acmicpc.net/problem/2493
 */
public class BOJ_2493 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] towels = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            towels[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int height = towels[i];

            while (!stack.isEmpty() && stack.peek()[1] < height) {  //현재 탑보다 작은 탑은 제거
                stack.pop();
            }

            if (stack.isEmpty()) {
                sb.append("0").append(" ");
            } else {
                sb.append(stack.peek()[0]).append(" ");
            }

            stack.push(new int[]{i + 1, height});  //현재 탑의 (인덱스, 높이) 추가
        }

        System.out.println(sb);
    }
}
