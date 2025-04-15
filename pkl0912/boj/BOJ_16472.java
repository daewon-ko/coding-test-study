package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_16472 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int lt = 0;
        int rt = 0;
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;

        while(rt<s.length()){
            set.add(s.charAt(rt));
            map.put(s.charAt(rt), map.getOrDefault(s.charAt(rt),0)+1);
            while(set.size()>n){
                map.put(s.charAt(lt), map.get(s.charAt(lt))-1);
                if(map.get(s.charAt(lt))==0){
                    set.remove(s.charAt(lt));
                }
                lt++;
            }
            max = Math.max(max, rt-lt+1);

            rt++;
        }
        System.out.println(max);
    }
    
}
