package pkl0912.pgs;

import java.util.*;

public class PGS_77486 {
    Map<String, String> map = new HashMap<>();
    Map<String, Integer> money = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        int n = enroll.length;
        for(int i = 0; i<n; i++){
            map.put(enroll[i], referral[i]);
        }
        
        for(int i = 0; i<seller.length; i++){           
            dfs(seller[i], amount[i]*100);
        }
        int[] answer = new int[n];
                      
        for(int i = 0; i<n; i++){
            answer[i] = money.getOrDefault(enroll[i], 0);
        }
        return answer;
    }
    void dfs(String name, int income){
        if(name.equals("-")||income<1){
            return;
        }
        int give = income/10;
        int get = income-give;
        money.put(name, money.getOrDefault(name, 0)+get);
        dfs(map.get(name), give);
    }
}