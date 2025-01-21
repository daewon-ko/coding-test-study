package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 문자열 폭발
public class BOJ_9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();

        int bombLength = bomb.length();

        for (int i = 0; i < input.length(); i++) {
            sb.append(String.valueOf(input.charAt(i)));

            if (sb.length() >= bombLength) {

                // 뒤에서부터 삭제 처리
                if (sb.substring(sb.length() - bombLength).equals(bomb)) {
                    sb.delete(sb.length() - bombLength, sb.length());
                }

            }
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);

        }





    }
}
