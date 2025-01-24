package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9935 {
    static final String NOTHING = "FRULA";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String test = br.readLine();
        String bomb = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < test.length(); i++) {
            char ch = test.charAt(i);
            sb.append(ch);

            if (sb.length() >= bomb.length()) {  //sb의 길이가 폭발 문자열보다 길면
                boolean sameFlag = true;
                for (int j = 0; j < bomb.length(); j++) {  //폭발 문자열의 길이만큼 반복
                    char ch1 = sb.charAt(sb.length() - bomb.length() + j);
                    char ch2 = bomb.charAt(j);
                    if (ch1 != ch2) {
                        sameFlag = false;
                        break;
                    }
                }
                if (sameFlag) {
                    sb.delete(sb.length() - bomb.length(), sb.length());
                }
            }
            
        }
        if(sb.length() == 0) {
            System.out.println(NOTHING);
        } else {
            System.out.println(sb.toString());
        }
    }
}

