package daewonko.boj;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1541 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();
        String numberArray[] = number.split("-");
        int answer = 0;
        for (int i = 0; i < numberArray.length; i++) {
            int temp = mySum(numberArray[i]);
            if (i == 0) {
                answer += temp;
            } else {
                answer -= temp;
            }
        }
        System.out.println(answer);

    }

    static int mySum(String s) {
        int answer = 0;
        String[] temp = s.split("\\+");
        for (int i = 0; i < temp.length; i++) {
            answer += Integer.parseInt(temp[i]);
        }
        return answer;
    }
}

