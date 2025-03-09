package daewonko.pgs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 프로그래머스 압축
public class PGS_17684 {
    public int[] solution(String msg) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();
        int index = 1;
        for (char c = 'A'; c <= 'Z'; c++) {
            dict.put(String.valueOf(c), index++);
        }

        for (int i = 0; i < msg.length(); ) {
            String w = "" + msg.charAt(i);
            int j = i + 1;
            while (j <= msg.length() && dict.containsKey(w)) {
                if (j < msg.length()) w += msg.charAt(j);
                j++;
            }
            if (!dict.containsKey(w)) {
                dict.put(w, index++);
                w = w.substring(0, w.length() - 1);
            }
            result.add(dict.get(w));
            i += w.length();
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
