package pkl0912.pgs;

import java.util.*;

class PGS_17684 {
    public int[] solution(String msg) {
        List<String> words = new ArrayList<>();
        words.add("-");
        for(int i = 0; i<26; i++){
            char c = (char)(i+65);
            words.add(Character.toString(c));
        }
        List<Integer>result = new ArrayList<>();
        int i = 0;
        while(i<msg.length()){
            int j = 1;
            while(i+j<=msg.length() && words.contains(msg.substring(i, i+j))){
                j++;
            }
            result.add(words.indexOf(msg.substring(i, i+j-1)));
            if(i+j-1<msg.length()){
                words.add(msg.substring(i, i+j));
            }
            i += j-1;
        }
    
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
