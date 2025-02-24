import java.util.Stack;

/*
프로그래머스 / 괄호 변환 / Level 2
https://school.programmers.co.kr/learn/courses/30/lessons/60058
 */
class PGS_60058 {

    public String solution(String p) {
        return recur(p);
    }

    private String recur(String str) {
        if (str.isEmpty()) {  //빈 문자열은 그대로 반환
            return str;
        }

        //'('와 ')' 개수 카운트
        int left = 0, right = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                right++;
            }
            if (left == right) {  //균현잡힌 괄호 문자열일 때 스톱
                break;
            }
        }

        String u = str.substring(0, left * 2);
        String v = str.substring(left * 2);

        if (isRight(u)) {  //u가 올바른 괄호 문자열일 경우 재귀 호출
            return u + recur(v);  //u에 이어 붙힌 후 반환
        } else {  //u가 올바른 괄호 문자열이 아닐 경우
            String s = "(" + recur(v) + ")";  //재귀 호출의 결과에 '(', ')'로 감싼다.
            String substring = u.substring(1, u.length() - 1);  //u의 양 끝 문자를 제거

            //괄호 방향을 뒤집는다.
            StringBuilder reversedU =new StringBuilder();
            for (char c : substring.toCharArray()) {
                if (c == '(') {
                    reversedU.append(')');
                } else {
                    reversedU.append('(');
                }
            }

            return s + reversedU;
        }
    }

    //올바른 문자열인지 판단
    private boolean isRight(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() &&stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        return stack.isEmpty();
    }
}

