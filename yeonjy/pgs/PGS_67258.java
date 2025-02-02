package yeonjy.pgs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PGS_67258 {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int idx = 1;
        HashMap<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        for(String s: gems)
            set.add(s);

        for(String s: set)
            map.put(s, idx++);

        int num = set.size();   //보석 개수

        int min_idx = 0, max_idx = 0;
        int sidx = 0, eidx = 0;
        int[] count = new int[num+1];
        int cnt = 0, differ = Integer.MAX_VALUE;

        while(eidx < gems.length){
            if(count[map.get(gems[eidx])] == 0)
                cnt++;
            count[map.get(gems[eidx])]++;
            eidx++;

            while(cnt == num){
                if(differ > eidx - sidx){
                    differ = eidx - sidx;
                    max_idx = eidx;
                    min_idx = sidx;
                }
                count[map.get(gems[sidx])]--;
                if(count[map.get(gems[sidx])] == 0)
                    cnt--;
                sidx++;
            }
        }

        answer[0] = min_idx + 1;
        answer[1] = max_idx;
        return answer;
    }
}
