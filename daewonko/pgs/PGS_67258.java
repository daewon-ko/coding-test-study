package daewonko.pgs;

import java.util.*;

public class PGS_67258 {


    public int[] solution(String[] gems) {
        int gemTypes = (int) Arrays.stream(gems).distinct().count();
        Map<String, Integer> gemCount = new HashMap<>();
        int start = 0, end = 0;
        int minLength = Integer.MAX_VALUE;
        int[] answer = new int[2];

        while (end < gems.length) {

            gemCount.put(gems[end], gemCount.getOrDefault(gems[end], 0) + 1);
            end++;


            while (gemCount.size() == gemTypes) {

                if (end - start < minLength) {
                    minLength = end - start;
                    answer[0] = start;
                    answer[1] = end-1 ;
                }


                gemCount.put(gems[start], gemCount.get(gems[start]) - 1);
                if (gemCount.get(gems[start]) == 0) {
                    gemCount.remove(gems[start]);
                }
                start++;
            }
        }

        return new int[]{answer[0] + 1, answer[1] + 1};
    }


}


