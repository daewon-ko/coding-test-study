package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 백준 줄어드는 수
public class BOJ_1174 {
    static int n;
    static List<Long> numbers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());


        for (int i = 0; i <= 9; i++) {
            solve(i, i);
        }

        Collections.sort(numbers);

        if (n > numbers.size()) {

            System.out.println(-1);

        } else {
            System.out.println(numbers.get(n - 1));
        }

    }

    public static void solve(long number, int lastDigit) {
        numbers.add(number);

    // lastDigit보다 nextNumber는 작기때문에 항상 줄어드는 수를 만족할 수 밖에 없다.
        for (int nextNumber = 0; nextNumber < lastDigit; nextNumber++) {
            solve(number * 10 + nextNumber, nextNumber);
        }


    }
}
