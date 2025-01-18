import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
백준 / 잃어버린 괄호 / 실버2
https://www.acmicpc.net/problem/1541
 */
public class BOJ_1541 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String[] split = input.split("-");  //빼기 연산으로 분할
        Integer total = null;

        for (String s : split) {
            //더하기 연산
            int sum = Arrays.stream(s.split("\\+"))
                    .mapToInt(Integer::parseInt)
                    .sum();

            if (total == null) {  //첫번째 연산일 때
                total = sum;
            } else {
                total -= sum;
            }
        }

        System.out.println(total);
    }
}
