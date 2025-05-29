import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
프로그래머스 / 수식 복원하기 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/340210
 */
class PGS_340210 {

    public String[] solution(String[] expressions) {
        int[] base = new int[10];
        List<String> target = new ArrayList<>();

        for (String s : expressions) {
            String[] parts = s.split(" ");
            if (parts[4].equals("X")) {
                target.add(s);  //나중에 복원할 수식
            } else {
                for (int i = 2; i <= 9; i++) {  //가능한 진법을 카운트
                    try {
                        //a + b = c
                        int a = Integer.parseInt(parts[0], i);
                        int b = Integer.parseInt(parts[2], i);
                        int c = Integer.parseInt(parts[4], i);

                        if (parts[1].equals("+") && a + b == c) {  //덧셈
                            base[i]++;
                        } else if (parts[1].equals("-") && a - b == c) {  //뺄셈
                            base[i]++;
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        }

        for (String s : target) {
            String[] parts = s.split(" ");
            for (int i = 2; i <= 9; i++) {  //X 포함 수식의 가능한 진법을 카운트
                try {
                    Integer.parseInt(parts[0], i);
                    Integer.parseInt(parts[2], i);
                    base[i]++;
                } catch (Exception ignored) {
                }
            }
        }

        int max = 0;
        for (int i = 2; i <= 9; i++) {
            max = Math.max(max, base[i]);
        }

        String[] answer = new String[target.size()];
        for (int i = 0; i < target.size(); i++) {
            String expr = target.get(i);
            String[] parts = expr.split(" ");
            Set<String> results = new HashSet<>();

            for (int j = 2; j <= 9; j++) {
                if (base[j] != max) {  //가장 우선순위가 높은 진법들만
                    continue;
                }

                try {  //진법 j로 변환
                    int a = Integer.parseInt(parts[0], j);
                    int b = Integer.parseInt(parts[2], j);
                    int result = parts[1].equals("+") ? a + b : a - b;
                    results.add(Integer.toString(result, j));
                } catch (Exception ignored) {
                }
            }

            if (results.size() == 1) {
                answer[i] = expr.substring(0, expr.length() - 1) + results.iterator().next();
            } else {
                answer[i] = expr.substring(0, expr.length() - 1) + "?";
            }
        }

        return answer;
    }
}
