import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
백준 / 좋은수열 / 골드4
https://www.acmicpc.net/problem/2661
 */
public class BOJ_2661 {

    private static int N;
    private static boolean isFound;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  //1 ~ 80

        recur(0, new StringBuilder());
    }

    private static void recur(int depth, StringBuilder sb) {
        if (isFound) {  //정답을 찾았으면 종료
            return;
        }

        if (depth == N) {
            System.out.println(sb);
            isFound = true;
            return;
        }

        for (int num = 1; num <= 3; num++) {  //작은 수부터 탐색
            sb.append(num);
            if (isGood(sb)) {  //좋은 수열일 경우
                recur(depth + 1, sb);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static boolean isGood(StringBuilder sb) {
        int len = sb.length();

        for (int i = 1; i <= len / 2; i++) {
            String left = sb.substring(len - 2 * i, len - i);
            String right = sb.substring(len - i, len);  //끝에서 i만큼
            if (left.equals(right)) {
                return false;
            }
        }
        return true;
    }
}