import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
백준 / 후위 표기식 / 골드2
https://www.acmicpc.net/problem/1918
 */
public class BOJ_1918 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] charArray = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : charArray) {
            if (Character.isAlphabetic(c)) {  //피연산자
                sb.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {  //'('를 만날 때까지 pop
                    sb.append(stack.pop());
                }
                stack.pop();  //'(' 제거
            } else {  //연산자
                //스택 위에 있는 연산자가 현재 연산자보다 우선순위가 높거나 같다면 pop
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(c)) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {  //남은 연산자 pop
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }

    private static int getPriority(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        }
        return 0;
    }
}
