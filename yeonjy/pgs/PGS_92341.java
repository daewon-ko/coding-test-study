package yeonjy.pgs;

import java.util.*;

class PGS_92341 {
    static class Parking implements Comparable<Parking> {
        int hour;
        int minute;
        int number;
        int info;  // 0: IN, 1: OUT
        
        Parking(int hour, int minute, int number, int info) {
            this.hour = hour;
            this.minute = minute;
            this.number = number;
            this.info = info;
        }
        
        @Override
        public int compareTo(Parking compare) {
            // 1) 오름차순 정렬
            int result = this.number - compare.number;
            if(result != 0) {
                return result;
            }
            // 2) 시간순 정렬
            return (this.hour * 60 + this.minute) - (compare.hour * 60 + compare.minute);
        }
    }
    
    static int[] fees;
    
    public int[] solution(int[] fees, String[] records) {
        this.fees = fees;
        // 1) records -> parkings에 넣기 + Set에 차 번호 넣기
        List<Parking> parkings = new ArrayList<>();
        Set<Integer> carNumbers = new HashSet<>();
        for (String record : records) {
            String[] rec = record.split(" ");
            // 시각 추출
            String[] time = rec[0].split(":");
            int hour = Integer.parseInt(time[0]);
            int min = Integer.parseInt(time[1]);
            
            // 차 번호 추출
            int carNum = Integer.parseInt(rec[1]);
            carNumbers.add(carNum);
            
            // 내역 추출
            int info = 0;
            if (rec[2].equals("OUT")) {
                info = 1;
            }
            parkings.add(new Parking(hour, min, carNum, info));
        }
        
        // 2) parking 정렬
        Collections.sort(parkings);
        
        // Map<Integer, Integer> feeMap = new HashMap<>();
        Map<Integer, Integer> timeMap = new HashMap<>();
        
        // 3) 차 별로 총 시간 계산
        for(int i = 0; i < records.length; i += 2) {
            Parking in = parkings.get(i);
            if (i + 1 >= records.length) {
                int time = getTime(in.hour, 23, in.minute, 59);
                timeMap.put(in.number, timeMap.getOrDefault(in.number, 0) + time);
                break;
            }
            Parking out = parkings.get(i + 1);
            if(in.number != out.number) {
                int time = getTime(in.hour, 23, in.minute, 59);
                timeMap.put(in.number, timeMap.getOrDefault(in.number, 0) + time);
                i--;
            } else {
                int time = getTime(in.hour, out.hour, in.minute, out.minute);
                timeMap.put(in.number, timeMap.getOrDefault(in.number, 0) + time);
            }
        }
        
        int[] answer = new int[carNumbers.size()];
        List<Integer> carNums = new ArrayList<>(carNumbers);
        Collections.sort(carNums);
        for (int i = 0; i < carNums.size(); i++) {
            answer[i] = getTotalFee(timeMap.get(carNums.get(i)));
        }
        return answer;
    }
    
    static int getTotalFee(int time) {
        int fee = fees[1];
        if (time <= fees[0]) {
            return fee;
        }
        fee += ((time - fees[0]) / fees[2]) * fees[3];
        if (((time - fees[0]) % fees[2]) > 0) {
            fee += fees[3];
        }
        return fee;
    }
    
    static int getTime(int inHour, int outHour, int inMin, int outMin) {
        int hour = outHour - inHour;
        int minute = outMin - inMin;        
        return hour * 60 + minute;
    }
}
