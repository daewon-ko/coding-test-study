package daewonko.pgs;

import java.util.*;

public class PGS_72411 {
    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> map = new HashMap<>();
        for (String order : orders) {
            char[] arr = order.toCharArray();
            Arrays.sort(arr);
            for (int len : course) {
                if (len <= arr.length) combine(arr, new StringBuilder(), 0, len, map);
            }
        }

        List<String> answerList = new ArrayList<>();
        for (int len : course) {
            int max = 2;
            List<String> candidates = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getKey().length() == len) {
                    if (entry.getValue() > max) {
                        max = entry.getValue();
                        candidates.clear();
                        candidates.add(entry.getKey());
                    } else if (entry.getValue() == max) {
                        candidates.add(entry.getKey());
                    }
                }
            }
            answerList.addAll(candidates);
        }

        Collections.sort(answerList);
        return answerList.toArray(new String[0]);
    }

    private void combine(char[] arr, StringBuilder sb, int idx, int len, Map<String, Integer> map) {
        if (sb.length() == len) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            sb.append(arr[i]);
            combine(arr, sb, i + 1, len, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
