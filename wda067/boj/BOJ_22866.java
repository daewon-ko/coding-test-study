import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
백준 / 탑 보기 / 골드3
https://www.acmicpc.net/problem/22866
 */
public class BOJ_22866 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //100,000
        int[] buildings = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[N + 1];  //보이는 건물 수
        int[] near = new int[N + 1];  //가장 가까운 보이는 건물 인덱스
        Arrays.fill(near, -100_000);

        Stack<Integer> stack = new Stack<>();  //볼 수 있는 건물 인덱스 저장

        //왼쪽으로 볼 때
        for (int i = 1; i <= N; i++) {
            //현재 건물보다 작은 건물은 pop
            while (!stack.isEmpty() && buildings[stack.peek()] <= buildings[i]) {
                stack.pop();
            }

            count[i] += stack.size();

            if (count[i] > 0) {
                near[i] = stack.peek();  //가장 가까운 건물 인덱스
            }
            stack.push(i);
        }

        stack.clear();

        //오른쪽으로 볼 때
        for (int i = N; i > 0; i--) {
            while (!stack.isEmpty() && buildings[stack.peek()] <= buildings[i]) {
                stack.pop();
            }

            count[i] += stack.size();

            if (count[i] > 0 && stack.peek() - i < i - near[i]) {  //기존보다 더 가까운 건물이 있을 때
                near[i] = stack.peek();
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(count[i]);
            if (count[i] > 0) {
                sb.append(" ").append(near[i]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}