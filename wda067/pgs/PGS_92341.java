import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
프로그래머스 / 주차 요금 계산 / Level 2
https://school.programmers.co.kr/learn/courses/30/lessons/92341
 */
class PGS_92341 {

    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTime = new HashMap<>();
        Map<String, Integer> totalTime = new HashMap<>();

        for (String record : records) {
            String[] parts = record.split(" ");
            int time = toMinutes(parts[0]);
            String car = parts[1];
            String action = parts[2];

            if (action.equals("IN")) {
                inTime.put(car, time);
            } else {
                int in = inTime.remove(car);
                totalTime.put(car, totalTime.getOrDefault(car, 0) + (time - in));
            }
        }

        // 출차 기록이 없는 차량 처리
        for (Entry<String, Integer> entry : inTime.entrySet()) {
            String car = entry.getKey();
            int in = entry.getValue();
            totalTime.put(car, totalTime.getOrDefault(car, 0) + (toMinutes("23:59") - in));
        }

        // 차량 번호 오름차순 정렬
        List<String> cars = new ArrayList<>(totalTime.keySet());
        Collections.sort(cars);

        int[] answer = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            int time = totalTime.get(cars.get(i));
            answer[i] = calculateFee(time, fees);
        }

        return answer;
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    private int calculateFee(int time, int[] fees) {
        int baseTime = fees[0], baseFee = fees[1];
        int unitTime = fees[2], unitFee = fees[3];

        if (time <= baseTime) {
            return baseFee;
        }
        return baseFee + (int) Math.ceil((time - baseTime) / (double) unitTime) * unitFee;
    }
}


