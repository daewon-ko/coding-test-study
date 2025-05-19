package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());

            String s = br.readLine();
            String sub = s.substring(1, s.length()-1);
            String[] arr = sub.isEmpty() ? new String[0] : sub.split(",");
            Deque<Integer> dq = new LinkedList<>();
            for(String numStr: arr){
                dq.add(Integer.parseInt(numStr));
            }
            boolean reverse = false;
            boolean error = false;
            for(char c: p.toCharArray()){
                if(c=='R'){
                    reverse = !reverse;
                }else{
                    if(dq.isEmpty()){
                        error = true;
                        break;
                    }
                    if(reverse){
                        dq.pollLast();
                    }else{
                        dq.pollFirst();
                    }
                }
            }
            if(error) System.out.println("error");
            else{
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                if(reverse){
                    while(!dq.isEmpty()){
                        sb.append(dq.pollLast());
                        if (!dq.isEmpty()) {
                            sb.append(",");
                        }
                    }
                }else{
                    while(!dq.isEmpty()){
                        sb.append(dq.pollFirst());
                        if (!dq.isEmpty()) {
                            sb.append(",");
                        }
                    }
                }
                sb.append("]");
                System.out.println(sb.toString());
            }
        }
    }
}
