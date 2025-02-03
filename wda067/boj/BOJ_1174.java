package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
백준 / 줄어드는 수 / 골드5
https://www.acmicpc.net/problem/1174
 */
public class BOJ_1174 {

    static int N;
    static int[] NUMBERS = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static List<Long> descendingNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        recur(0, 0);

        Collections.sort(descendingNumbers);

        if (N > descendingNumbers.size()) {  //줄어드는 수의 크기보다 클 경우
            System.out.println(-1);
        } else {
            System.out.println(descendingNumbers.get(N - 1));
        }
    }

    static void recur(long num, int index) {
        if (!descendingNumbers.contains(num)) {
            descendingNumbers.add(num);
        }

        if (index >= NUMBERS.length) {  //index가 숫자 배열의 범위를 벗어나면 리턴
            return;
        }

        //현재 index의 숫자를 1의 자리에 포함하여 재귀 호출
        recur(num * 10 + NUMBERS[index], index + 1);
        //index만 증가시켜 재귀 호출
        recur(num, index + 1);
    }
}

/*
recur(0, 0)
    recur(9, 1)
        recur(98, 2)
            recur(987, 3)
            recur(98, 3)
        recur(9, 2)
            recur(97, 3)
            recur(9, 3)
    recur(0, 1)
        recur(8, 2)
            recur(87, 3)
            recur(8, 3)
        recur(0, 2)
            recur(7, 3)
            recur(0, 3)
 */