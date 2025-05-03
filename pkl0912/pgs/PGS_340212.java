package pkl0912.pgs;

import java.util.*;

class PGS_340212{
    public int solution(int[] diffs, int[] times, long limit) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int diff: diffs){
            if(diff<min){
                min = diff;
            }
            if(diff>max){
                max = diff;
            }
        }
        int answer = min;
        int left = min;
        int right = max;
        while(left<=right){
            int mid = (left+right)/2;
            if(cal(diffs, times, mid, limit)){
                answer = mid;
                right = mid-1;
            }else{
                left = mid+1;
            }
            
        }      
        return answer;
    }
    public boolean cal(int[] diffs, int[] times, int level, long limit){
        long sum = 0;
        int n = diffs.length;
        int prevTime = 0;
        for(int i = 0; i<n; i++){
            int diff = diffs[i];
            if(level>=diff){
                sum+= times[i];
            }else{
                sum+= (long)(prevTime+times[i])*(diff-level)+times[i];       
            }
            if(sum>limit) return false;
            prevTime = times[i];
        }
        return true;
    }
}
