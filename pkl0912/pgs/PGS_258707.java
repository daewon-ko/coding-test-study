package pkl0912.pgs;
import java.util.*;

public class PGS_258707{
    Set<Integer> original, additional;
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int len = cards.length;
        int idx = len/3;
        original = new HashSet<>();
        additional = new HashSet<>();
        int target = len+1;
        for(int i = 0; i<idx; i++){
            original.add(cards[i]);
        }
        while(true){
            answer++;
            if(idx>=len){
                break;
            }
            additional.add(cards[idx]);
            additional.add(cards[idx+1]);
            idx+=2;
            boolean flag = false;
            //최초 카드에서 해결  
            for(int i:original){
                if(original.contains(target-i)){
                    flag = true;
                    original.remove(i);
                    original.remove(target-i);
                    break;
                }
            }
            if(!flag){
                if(coin>0){
                    for(int i:original){
                        if(additional.contains(target-i)){
                            additional.remove(target-i);
                            original.remove(i);
                            flag = true;
                            coin--;
                            break;
                        }
                    }
                }
            }
            if(!flag){
                if(coin>1){
                    for(int i:additional){
                        if(additional.contains(target-i)){
                            additional.remove(i);
                            additional.remove(target-i);
                            coin-=2;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if(!flag){
                break;
            }
        }
        return answer;
    }

}