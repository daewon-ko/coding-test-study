package yeonjy.pgs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PGS_42890 {
    static ArrayList<String> candidates = new ArrayList<>();
    static String[][] relation;

    public int solution(String[][] relation) {
        this.relation = relation;
        ArrayList<Integer> list = new ArrayList<>();
        dfs(0, list);
        return candidates.size();
    }

    static void dfs(int idx, ArrayList<Integer> list) {
        checkCandidate(list);

        for (int i = idx; i < relation[0].length; i++) {
            list.add(i);
            dfs(i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    static void checkCandidate(ArrayList<Integer> list) {
        String key = "";
        for (int i : list) {
            key += String.valueOf(i);
        }

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < relation.length; i++) {
            String s = "";
            for (int j : list) {
                s += relation[i][j];
            }

            if (map.containsKey(s)) {
                return;
            }
            map.put(s, 0);
        }

        for (String s : candidates) {
            int count = 0;
            for (int i = 0; i < key.length(); i++) {
                String sub = String.valueOf(key.charAt(i));
                if (s.contains(sub)) {
                    count++;
                }
            }
            if (count == s.length()) {
                return;
            }
        }
        candidates.add(key);
    }
}
