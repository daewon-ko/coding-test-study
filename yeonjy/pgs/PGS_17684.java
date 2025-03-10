package yeonjy.pgs;

import java.util.*;

class PGS_17684 {
    static int cnt = 1;
    static Map<String, Integer> dict = new HashMap<>();
    static List<Integer> answerWords = new ArrayList<>();
    
    public int[] solution(String msg) {
        initDictionary();
        
        for (int i = 0; i < msg.length(); ) {
            int j;
            for (j = i; j < msg.length(); j++) {
                String substr = msg.substring(i, j+1);
                if (!dict.containsKey(substr)) {
                    break;
                }
            }
            
            String substr = msg.substring(i, j);
            int num = dict.get(substr);
            answerWords.add(num);
            
            if (j < msg.length()) {
                dict.put(msg.substring(i, j+1), cnt++);
            }
            
            i = j;
        }
        int[] answer = new int[answerWords.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerWords.get(i);
        }
        return answer;
    }
    
    static void initDictionary() {
        for (int i = 0; i < 26; i++) {
            char word = (char)('A' + i);
            dict.put(String.valueOf(word), cnt++);
        }
    }
}
