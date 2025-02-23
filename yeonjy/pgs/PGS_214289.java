package yeonjy.pgs;

class PGS_214289 {
    static final int MAX = 9999999;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = MAX;
        int[][] dp = new int[1001][60];
        
        int unit =(temperature < t1)? -1 : 1;

        
        for(int i = 0; i <= 1000; i++)
            for(int j = 0; j < 60; j++) dp[i][j] = MAX;
        
        t1 += 15;
        t2 += 15;
        temperature += 15;
        
        dp[0][temperature] = 0;
        dp[0][temperature-unit] = a;
        
        for(int i = 1; i < onboard.length; i++){
        	
            int start = (unit == 1)? t1 : temperature;
            int end = (unit == 1)? temperature : t2;
            
            for(int j = start; j <= end; j++){
                if(onboard[i] == 1 && (t1 > j || j > t2)) continue;
                if(j == temperature){
                    dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-unit]);
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j-unit],dp[i-1][j+unit]+a);
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j]+b);
                }
                if(i == onboard.length-1) answer = Math.min(answer,dp[i][j]);
            }
        }
        return answer;
    }
}
