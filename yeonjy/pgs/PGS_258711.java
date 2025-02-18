package yeonjy.pgs;

import java.util.*;

public class PGS_258711 {
    public int[] solution(int[][] edges) {
        Map<Integer, Integer> out = new HashMap<>();
        Map<Integer, Integer> in = new HashMap<>();
        int[] answer = new int[4];

        for (int[] edge : edges) {
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
        }

        boolean isCheked = false;
        for (int node : out.keySet()) {
            if (out.get(node) > 1) {
                if (!isCheked && !in.containsKey(node)) {
                    answer[0] = node;
                    isCheked = true;
                } else {
                    answer[3]++;  // 8자
                }
            }
        }

        for (int node : in.keySet()) {
            if (!out.containsKey(node)) {
                answer[2]++;  // 막대
            }
        }
        answer[1] = out.get(answer[0]) - answer[2] - answer[3];  // 도넛
        return answer;
    }
}
