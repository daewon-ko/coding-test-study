package yeonjy.pgs;

class PGS_72414 {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int playTime = timeToSecond(play_time);
        int advTime = timeToSecond(adv_time);
        int[] times = new int[360000];
        
        if(play_time.equals(adv_time)) return "00:00:00";
        for(String log : logs) {
            String[] splitTime = log.split("-");
            int startTime = timeToSecond(splitTime[0]);
            int endTime = timeToSecond(splitTime[1]);
            for(int i = startTime; i < endTime; i++) {
                times[i]++;
            }
        }
        
        int maxIdx = 0;
        long totalTime = 0;
        for(int i = 0; i < advTime; i++) {
            totalTime += times[i];
        }
        
        long maxTime = totalTime;
        for(int i = advTime; i < playTime; i++) {
            totalTime += times[i] - times[i - advTime];
            if(totalTime > maxTime) {
                maxTime = totalTime;
                maxIdx = i - advTime + 1;
            }
        }
        return secondToTime(maxIdx);
    }
    
    public int timeToSecond(String time) {
        String[] s = time.split(":");
        return (3600 * Integer.parseInt(s[0]) + Integer.parseInt(s[1]) * 60 + Integer.parseInt(s[2]));
    }
    
    public String secondToTime(int time) {
        int hour = time / 3600;
        time %= 3600;
        
        int minute = time / 60;
        time %= 60;
        
        int second = time;
        
        return (hour < 10 ? "0" : "") + hour + ":" + 
            (minute < 10 ? "0" : "") + minute + ":" +
            (second < 10 ? "0" : "") + second;
        
    }
}
