package yeonjy.pgs;

import java.util.*;

public class PGS_72411 {
    static Map<String, Integer> map;
    static int max;
    static int end;
    
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();

        for (int c : course) {
            map = new HashMap<>();
            max = 0;
            end = c;
            
            for (String order : orders) {
                char[] strs = order.toCharArray();
                Arrays.sort(strs);
                order = new String(strs);
                dfs(order, "", -1, 0);
            }
            for (String key : map.keySet()) {
                int value = map.get(key);
                if (value > 1 && max == value) {
                    answer.add(key);
                }
            }
        }
        Collections.sort(answer);
        return answer.toArray(new String[answer.size()]);
    }

    static void dfs(String order, String key, int index, int depth) {
        if (depth == end) {
            int value = map.getOrDefault(key, 0) + 1;
            map.put(key, value);
            max = Math.max(max, value);
        }
        for (int i = index + 1; i < order.length(); i++) {
            dfs(order, key + order.charAt(i), i, depth + 1);
        }
    }

}
