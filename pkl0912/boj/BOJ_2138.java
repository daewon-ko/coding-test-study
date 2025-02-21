package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_2138{
    static int n;
    static char[] goal;
    static char[] first;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        first = br.readLine().toCharArray();
        goal = br.readLine().toCharArray();
        int result1 = simulate(false);
        int result2 = simulate(true);
        int result = Math.min(result1, result2);
        System.out.println(result==Integer.MAX_VALUE ? -1 : result);
        

        
    }
    
    static int simulate(boolean switched){
        int cnt = 0;
        char[] bulbs = first.clone();
        if(switched){
            flip(bulbs, 0);
            cnt++;
        }
        for(int i = 1; i<n; i++){
            if(bulbs[i-1]!= goal[i-1]){
                flip(bulbs, i);
                cnt++;
            }
        }
        if(!isGoal(bulbs)){
            return Integer.MAX_VALUE;
        }
        return cnt;
    }
    static void flip(char[] bulbs, int idx) {
        bulbs[idx] = (bulbs[idx] == '0') ? '1' : '0';
        if (idx > 0) {
            bulbs[idx - 1] = (bulbs[idx - 1] == '0') ? '1' : '0';
        }
        if (idx < n - 1) {
            bulbs[idx + 1] = (bulbs[idx + 1] == '0') ? '1' : '0';
        }
    }

    static boolean isGoal(char[] bulbs) {
        for (int i = 0; i < n; i++) {
            if (bulbs[i] != goal[i]) {
                return false;
            }
        }
        return true;
    }
}