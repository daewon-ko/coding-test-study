package pkl0912.pgs;

import java.util.*;
public class PGS_81303 {
    public String solution(int n, int k, String[] cmd) {
        
        StringBuilder builder = new StringBuilder();
        int rowNum = n;
        Stack<Integer> deleted = new Stack<>();
        
        for(String tmp : cmd){
            if(tmp.length() > 1){
                int x = Integer.parseInt(tmp.substring(2));
                if(tmp.charAt(0) == 'U') k -= x;
                else k += x;
            } else if (tmp.charAt(0) == 'C'){
                deleted.push(k);
                rowNum--;
                if(rowNum == k) k--;
            } else {
                rowNum++;
                if(deleted.pop() <= k) k++;
            }
        }
        
        for(int i = 0 ; i < rowNum ; i++)
            builder.append("O");
        while(!deleted.empty())
            builder.insert(deleted.pop(), "X");
        
        return builder.toString();
    }
}