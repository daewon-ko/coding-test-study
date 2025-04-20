package pkl0912.pgs;

import java.util.*;
public class PGS_42884 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>(){
            @Override
            public int compare(int[] r1, int[]r2){
                return r1[1] - r2[1];
            }
        });
        int answer = 0;
        int pos = Integer.MIN_VALUE;
        for(int[] r: routes){
            if(pos<r[0]){
                pos=r[1];
                answer++;
            }
        }
        
        return answer;
    }
}