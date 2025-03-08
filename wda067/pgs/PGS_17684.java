import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
프로그래머스 / 압축 / Level 2
https://school.programmers.co.kr/learn/courses/30/lessons/17684
 */
class PGS_17684 {

    public List<Integer> solution(String msg) {
        List<Integer> result = new ArrayList<>();

        //사전 초기화
        Map<String, Integer> dict = new HashMap<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < alphabet.length(); i++) {
            dict.put(String.valueOf(alphabet.charAt(i)), i + 1);
        }

        int len = msg.length();
        int dictIdx = 27;  //새로운 색인 번호
        int i = 0;

        while (i < len) {
            String w = "";
            int j = i;

            //사전에 있는 가장 긴 문자열 찾기
            while (j < len && dict.containsKey(msg.substring(i, j + 1))) {
                w = msg.substring(i, j + 1);
                j++;
            }

            result.add(dict.get(w));  //색인 번호 출력

            if (j < msg.length()) {  //새로운 문자열 추가
                dict.put(msg.substring(i, j + 1), dictIdx++);
            }

            i = j;  //다음 탐색 시작 위치 갱신
        }

        return result;
    }
}
