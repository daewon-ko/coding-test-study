package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_9519 {
    static int n;
    static String result = "";
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        List<String>states = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        String current = s;
        while(!seen.contains(current)){
            seen.add(current);
            states.add(current);
            current = returnString(current);
        }
        int period = states.size();
        System.out.println(states.get(n%period));

        
    }
    static String returnString(String s){
        String move = "";
        String remain = "";
        for(int i = 0; i<s.length();i++){
            if(i%2==0) remain+=s.charAt(i);
            else move+=s.charAt(i);
        }
        StringBuffer sb = new StringBuffer(move);
        String reverse = sb.reverse().toString();
        String answer = remain+reverse;
        return answer;
    }
}
