import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PGS_72411 {

    //조합을 생성하는 메서드
    private void generateCombinations(char[] order, int courseSize, int start, StringBuilder current,
                                      Map<String, Integer> combinationCount) {

        if (current.length() == courseSize) {
            combinationCount.put(current.toString(), combinationCount.getOrDefault(current.toString(), 0) + 1);
            return;
        }

        for (int i = start; i < order.length; i++) {
            current.append(order[i]);
            generateCombinations(order, courseSize, i + 1, current, combinationCount);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();

        for (int courseSize : course) {
            Map<String, Integer> combinationCount = new HashMap<>();
            int maxCount = 0;

            // 각 주문에 대해 조합 생성
            for (String order : orders) {
                char[] sortedOrder = order.toCharArray();
                Arrays.sort(sortedOrder);
                generateCombinations(sortedOrder, courseSize, 0, new StringBuilder(), combinationCount);
            }

            // 가장 많이 주문된 조합 찾기
            for (int count : combinationCount.values()) {
                if (count > maxCount) {
                    maxCount = count;
                }
            }

            //코스 요리 후보 선정 (최소 2번 이상 주문된 조합만)
            if (maxCount >= 2) {
                for (Map.Entry<String, Integer> entry : combinationCount.entrySet()) {
                    if (entry.getValue() == maxCount) {
                        result.add(entry.getKey());
                    }
                }
            }
        }

        //결과 정렬
        Collections.sort(result);
        return result.toArray(new String[0]);
    }
}
