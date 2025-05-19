import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
백준 / AC / 골드5
https://www.acmicpc.net/problem/5430
 */
public class BOJ_5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            int[] x = new int[n + 1];

            Deque<Integer> deque = new ArrayDeque<>();
            String input = br.readLine();
            input = input.replace("[", "");
            input = input.replace("]", "");
            String[] split = input.split(",");

            for (String s : split) {
                if (s.isEmpty()) {
                    continue;
                }
                deque.addLast(Integer.parseInt(s));
            }

            boolean isFront = true;
            boolean isError = false;
            for (char c : p.toCharArray()) {
                if (c == 'R') {  //순서 뒤집기
                    isFront = !isFront;
                } else {  //첫번째 수 버리기
                    if (isFront) {
                        if (deque.isEmpty()) {
                            sb.append("error").append("\n");
                            isError = true;
                            break;
                        }
                        deque.removeFirst();
                    } else {
                        if (deque.isEmpty()) {
                            sb.append("error").append("\n");
                            isError = true;
                            break;
                        }
                        deque.removeLast();
                    }
                }
            }

            if (isError) {
                continue;
            }
            if (isFront) {
                sb.append(deque.toString().replace(" ", "")).append("\n");
            } else {
                sb.append("[");
                while (!deque.isEmpty()) {
                    sb.append(deque.removeLast());
                    if (!deque.isEmpty()) {
                        sb.append(",");
                    }
                }
                sb.append("]").append("\n");
            }
        }
        System.out.println(sb);
    }
}