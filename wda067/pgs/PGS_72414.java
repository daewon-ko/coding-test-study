/*
프로그래머스 / 광고 삽입 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/72414
 */
class PGS_72414 {

    private static final int TIME = 360_000;  //100시간 -> 초

    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = parseSec(play_time);
        int advSec = parseSec(adv_time);

        long[] totalView = new long[TIME];  //시청자 수 누적합

        for (String log : logs) {
            String[] split = log.split("-");
            int s = parseSec(split[0]);
            int e = parseSec(split[1]);

            for (int i = s; i < e; i++) {  //초당 시청자 수 카운트
                totalView[i]++;
            }
        }

        int maxIndex = 0;  //광고가 시작되는 최적 시간
        long sum = 0;  //현재 구간 누적 시청 시간
        for (int i = 0; i < advSec; i++) {
            sum += totalView[i];
        }
        long maxSum = sum;

        for (int i = advSec; i < playSec; i++) {  //모든 광고 시작 시간 탐색
            //슬라이딩 윈도우
            sum += totalView[i];
            sum -= totalView[i - advSec];

            if (sum > maxSum) {  //최대 누적 시청 시간 갱신
                maxSum = sum;
                maxIndex = i - advSec + 1;
            }
        }

        return parseTime(maxIndex);
    }

    private int parseSec(String time) {
        String[] split = time.split(":");
        int h = Integer.parseInt(split[0]) * 3600;
        int m = Integer.parseInt(split[1]) * 60;
        int s = Integer.parseInt(split[2]);
        return h + m + s;
    }

    private String parseTime(int seconds) {
        int h = seconds / 3600;
        seconds %= 3600;
        int m = seconds / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
