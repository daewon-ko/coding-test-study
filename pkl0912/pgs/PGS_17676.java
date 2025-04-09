package pkl0912.pgs;
import java.util.*;
import java.time.*;
public class PGS_17676 {
    List<int[]> list = new ArrayList<>();
    int answer = 0;
    public int solution(String[] lines) {
        
        for(String l: lines){
            String[] datetime = l.split(" ");
            String completedtime = datetime[1];
            String duration = datetime[2].split("s")[0];
            int durationTime = (int)(Double.parseDouble(duration)*1000);
            String[] time = completedtime.split(":");
            int hour = Integer.parseInt(time[0])*1000*60*60;
            int min = Integer.parseInt(time[1])*1000*60;
            int sec = (int)(Double.parseDouble(time[2])*1000);
            int end = hour+min+sec;
            int start = end+1 - durationTime;     
            list.add(new int[]{start, end});
        }
        
        search(0);
        search(1);
        return answer;
    }
    public void search(int std){
        for(int i = 0; i<list.size();i++){
            int cnt = 0;
            int cur = list.get(i)[std];
            int end = cur+1000;
            for(int j = 0; j<list.size(); j++){
                if(list.get(j)[0]>=cur && list.get(j)[0]<end){
                    cnt++;
                }else if(list.get(j)[1]>=cur && list.get(j)[1]<end){
                    cnt++;
                }else if(list.get(j)[0]<=cur && list.get(j)[1]>=end){
                    cnt++;
                }
                
            }
            answer = Math.max(answer, cnt);
        }
    }
}