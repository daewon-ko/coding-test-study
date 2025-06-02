package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_12904 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        while(t.length()!=s.length()){
            if(t.charAt(t.length()-1)=='A') t = reA(t);
            else t = reB(t);
        }
        if(t.equals(s)) System.out.println(1);
        else System.out.println(0);
    }
    static String reA(String word){
        return word.substring(0, word.length()-1);
    }
    static String reB(String word){
        String newWord = word.substring(0, word.length()-1);
        String answer = "";
        for(int i = newWord.length()-1; i>=0; i--){
            answer+=newWord.charAt(i);
        }
        return answer;
    }
}
