import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
프로그래머스 / 후보키 / Level2
https://school.programmers.co.kr/learn/courses/30/lessons/42890
 */
class PGS_42890 {

    static int R, C;
    static String[][] relation;
    static List<Integer> candidateKeys = new ArrayList<>();

    public int solution(String[][] input) {
        relation = input;
        R = relation.length;
        C = relation[0].length;

        //모든 컬럼 조합(2^C)을 탐색
        for (int subset = 1; subset < (1 << C); subset++) {
            if (isUnique(subset) && isMinimal(subset)) {
                candidateKeys.add(subset);
            }
        }

        return candidateKeys.size();
    }

    //유일성 검사
    static boolean isUnique(int subset) {
        Set<String> set = new HashSet<>();

        //subset에 포함된 모든 컬럼으로 문자열 생성
        for (int i = 0; i < R; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < C; j++) {
                if ((subset & (1 << j)) != 0) {  //j번 컬럼이 선택된 경우
                    sb.append(relation[i][j]).append(",");
                }
            }
            set.add(sb.toString());
        }

        return set.size() == R;
    }

    //최소성 검사
    static boolean isMinimal(int subset) {
        return candidateKeys.stream()
                .noneMatch(key -> (key & subset) == key);  //기존 후보키가 현재 키의 부분집합이면 최소성 X
    }
}