package yeonjy.pgs;

import java.util.*;

class PGS_60058 {
    public String solution(String p) {
        if (p.equals("")) {
            return p;
        }
        
        // u 만들기
        int pre = 0;
        int post = 0;
        String u = "";
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                pre++;
            } else {
                post++;
            }
            if (pre == post) {
                u = p.substring(0, i + 1);
                break;
            }
        }
        
        // v 만들기
        String v = p.substring(u.length());
        
        // u가 올바른 괄호 문자열인지 확인
        boolean isCorrectStr = isCorrect(u);
        if (isCorrectStr) {
            return u + solution(v);
        } else {
            StringBuilder newStr = new StringBuilder();
            newStr.append("(").append(solution(v)).append(")").append(reverse(u.substring(1, u.length() - 1)));
            return newStr.toString();
        }
    }
    
    static boolean isCorrect(String str) {
        int pre = 0;
        int post = 0;
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                pre++;
            } else {
                post++;
            }
            if (post > pre) {
                return false;
            }
        }
        return true;
    }
    
    static String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                sb.append(")");
            } else {
                sb.append("(");
            }
        }
        return sb.toString();
    }
}
