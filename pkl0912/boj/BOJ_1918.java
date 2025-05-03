package pkl0912.boj;

import java.io.*;
import java.util.*;


public class BOJ_1918 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            Character now = s.charAt(i);
            switch(now){
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!stack.isEmpty() && priority(stack.peek())>=priority(now)){
                        sb.append(stack.pop());
                    }
                    stack.push(now);
                    break;
                case '(':
                    stack.push(now);
                    break;
                case ')':
                    while(!stack.isEmpty() && stack.peek()!='('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(now);
                    
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());

        
    }
    static int priority(Character c){
        if(c=='('|| c==')') return 0;
        if(c=='+' || c=='-') return 1;
        if(c=='*'|| c=='/') return 2;
        return -1;
    }
}
