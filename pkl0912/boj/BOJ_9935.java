package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_9935 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sentence = br.readLine();
        String bomb = br.readLine();
        char[] brr = bomb.toCharArray();
        char[] arr = sentence.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        char lastchar = bomb.charAt(bomb.length()-1);
        for(char c : sentence.toCharArray()){
            sb.append(c);
            if(c==lastchar && sb.length()>=bomb.length()){
                if(sb.substring(sb.length()-bomb.length()).equals(bomb)){
                    sb.delete(sb.length()-bomb.length(), sb.length());
                }
            }
        }
        if(sb.length()==0) System.out.println("FRULA");
        else System.out.println(sb.toString());
    }
}
