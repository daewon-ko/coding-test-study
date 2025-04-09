package pkl0912.pgs;

public class PGS_12979 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int position = 1;
        int idx = 0;
        int range = 2*w+1;
        while(position<=n){
            if(idx<stations.length && stations[idx]-w<=position){
                position=stations[idx]+w+1;
                idx++;               
            }else{
                answer++;
                position+=range;
            }          
        }

        return answer;
    }
}

