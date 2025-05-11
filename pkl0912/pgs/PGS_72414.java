package pkl0912.pgs;

public class PGS_72414 {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSeconds = timeToSeconds(play_time.split(":"));
        int advSeconds = timeToSeconds(adv_time.split(":"));

        long[] playCount = new long[playSeconds+1];

        for(String log : logs){
            String[] times = log.split("-");
            playCount[timeToSeconds(times[0].split(":"))+1]++;
            int endSeconds = timeToSeconds(times[1].split(":"));
            if(endSeconds+1 <= playSeconds) playCount[endSeconds+1]--;
        }

        for(int i = 1 ; i <= playSeconds ; i++)
            playCount[i] += playCount[i-1];
        for(int i = 1 ; i <= playSeconds ; i++)
            playCount[i] += playCount[i-1];

        long maxCountSum = -1;
        int answer = 0;

        for(int i = 0 ; i <= playSeconds - advSeconds ; i++){
            long countSum = playCount[i+advSeconds] - playCount[i];
            if(countSum > maxCountSum){
                maxCountSum = countSum;
                answer = i;
            }
        }
        return secondsToString(answer);
    }
    public int timeToSeconds(String[] time){
        return Integer.parseInt(time[0])*3600 + Integer.parseInt(time[1])*60 + Integer.parseInt(time[2]);
    }
    public String secondsToString(int seconds){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d:", seconds/3600));
        sb.append(String.format("%02d:", (seconds%=3600)/60));
        sb.append(String.format("%02d", (seconds%=60)));
        return sb.toString();
    }
}