package pgs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
프로그래머스 / 보석 쇼핑 / Level3
https://school.programmers.co.kr/learn/courses/30/lessons/67258
 */
class PGS_67258 {

    public int[] solution(String[] gems) {
        Set<String> gemTypes = new HashSet<>(Arrays.asList(gems));
        int typeCount = gemTypes.size();  //보석의 종류

        Map<String, Integer> gemCount = new HashMap<>();
        int start = 0, end = 0;
        int minLength = Integer.MAX_VALUE;
        int[] result = new int[2];

        while (end < gems.length) {
            //현재 구간에 보석을 추가
            String lastGem = gems[end];
            gemCount.put(lastGem, gemCount.getOrDefault(lastGem, 0) + 1);

            while (gemCount.size() == typeCount) {  //모든 종류의 보석을 포함할 경우
                int length = end - start;
                if (length < minLength) {  //최소 구간 길이 갱신
                    minLength = length;
                    result[0] = start + 1;
                    result[1] = end + 1;
                }

                //구간 시작점 보석 제거
                String firstGem = gems[start];
                gemCount.put(firstGem, gemCount.get(firstGem) - 1);
                if (gemCount.get(firstGem) == 0) {
                    gemCount.remove(firstGem);
                }

                start++;  //구간 축소
            }

            end++;  //구간 확장
        }

        return result;
    }
}