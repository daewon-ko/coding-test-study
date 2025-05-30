import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
백준 / 0 만들기 / 골드5
https://www.acmicpc.net/problem/7490
 */
public class BOJ_7490 {

    private static int N;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            N = Integer.parseInt(br.readLine());
            dfs(1, "1");
            result.append("\n");
        }

        System.out.println(result);
    }

    private static void dfs(int num, String expr) {
        if (num == N) {
            String replace = expr.replace(" ", "");
            if (calculate(replace) == 0) {
                result.append(expr).append("\n");
            }
            return;
        }

        int next = num + 1;
        dfs(next, expr + " " + next);
        dfs(next, expr + "+" + next);
        dfs(next, expr + "-" + next);
    }

    private static int calculate(String expr) {
        int total = 0;
        int num = 0;
        char sign = '+';  //처음 숫자는 양수

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isDigit(c)) {  //숫자일 경우
                num = num * 10 + (c - '0');
            }

            //연산이거나 마지막일 때
            if (!Character.isDigit(c) || i == expr.length() - 1) {
                if (sign == '+') {
                    total += num;
                } else if (sign == '-') {
                    total -= num;
                }

                sign = c;  //다음 연산
                num = 0;  //숫자 초기화
            }
        }

        return total;
    }
}
