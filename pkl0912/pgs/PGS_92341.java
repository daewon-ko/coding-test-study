package pkl0912.pgs;

import java.util.*;
public class PGS_92341 {
    public int[] solution(int[] fees, String[] records) {
        Map<String, List<String>> map = new TreeMap<>();
        List<Integer> result = new ArrayList<>();
        for(String record: records){
            String[] recordArr = record.split(" ");
            String time = recordArr[0];
            String car = recordArr[1];
            String action = recordArr[2];
            map.computeIfAbsent(car, k->new ArrayList<>()).add(time+" "+action);
        }
        for(String car: map.keySet()){
            List<String> list = map.get(car);
            int lastTime = -1;
            int totalTime = 0;
            int amount = 0;
            for(int i = 0; i<list.size(); i++){
                int hour = Integer.parseInt(list.get(i).split(" ")[0].split(":")[0])*60;
                int min = Integer.parseInt(list.get(i).split(" ")[0].split(":")[1]);
                int time = hour+min;
                String action = list.get(i).split(" ")[1];
                if(action.equals("IN")){
                    lastTime = time;
                }else{
                    totalTime+= (time-lastTime);
                    lastTime = -1;
                }                
            }
            System.out.println(lastTime);
            if(lastTime !=-1){
                totalTime+= ((23*60+59)-lastTime);
            }
            System.out.println(totalTime);
            
            if(totalTime<=fees[0]) amount = fees[1];
            else{
                amount = fees[1]+(int)Math.ceil((double)(totalTime-fees[0])/fees[2])*fees[3];
            }
            System.out.println(amount);
            result.add(amount);
            
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}