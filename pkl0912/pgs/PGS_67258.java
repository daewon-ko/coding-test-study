package pkl0912.pgs;
import java.util.*;
public class PGS_67258{

    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> gemset = new HashSet<>(Arrays.asList(gems));
        int gemcnt = gemset.size();
        Map<String, Integer> gemCount = new HashMap<>();
        int lt = 0;
        int rt = 0;
        int min = Integer.MAX_VALUE;
        while(rt<gems.length){
            gemCount.put(gems[rt], gemCount.getOrDefault(gems[rt],0)+1);
            rt++;
            while(gemcnt == gemCount.size()){
                if(rt-lt<min){  
                    answer[0] = lt+1;
                    answer[1] = rt;
                    min = rt-lt;
                }
                gemCount.put(gems[lt], gemCount.get(gems[lt])-1);
                if(gemCount.get(gems[lt])==0){
                    gemCount.remove(gems[lt]);
                }
                lt++;                
            }
        }
        return answer;
    }
}
