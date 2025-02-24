package pkl0912.pgs;

import java.util.*;

class PGS_60058 {
    public String solution(String p) {
        if(checkRight(p)){
            return p;
        }else{
            return makeRight(p);
        }
        
    }
    public boolean checkRight(String p){
        Stack<Character> stack = new Stack<>();
        for(Character c: p.toCharArray()){
            if(c=='('){
                stack.push(c);
            }else{
                if(!stack.isEmpty() && stack.peek()=='('){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
        
    }
    public String makeRight(String p){
        if(p.equals("")) return p;
        
        int lcnt = 0;
        int rcnt = 0;
        String u = "";
        String v = "";
        for(int i = 0; i<p.length(); i++){
            if(p.charAt(i)=='(') lcnt++;
            else rcnt++;
            if(lcnt==rcnt){
                u = p.substring(0,i+1);
                v = p.substring(i+1);
                break;
            }
        }
        if(checkRight(u)){
            return u+ makeRight(v);
        }else{
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(makeRight(v)).append(")");
            String last = u.substring(1, u.length()-1);
            String answer = "";
            for(char c: last.toCharArray()){
                sb.append(c=='('?')':'(');
            }
            return sb.toString();
            
        }
    }

}

