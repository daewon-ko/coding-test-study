import java.util.Arrays;
import java.util.Comparator;

/*
프로그래머스 / 단속카메라 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/42884
 */
class PGS_42884 {

    public int solution(int[][] routes) {
        //진출 지점을 기준으로 정렬
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));

        int count = 0;
        int min = Integer.MIN_VALUE;

        for (int[] route : routes) {
            if (min < route[0]) {  //기존 카메라로 단속 불가
                count++;  //카메라 추가
                min = route[1];  //카메라를 진출 지점에 위치
            }
        }

        return count;
    }
}

