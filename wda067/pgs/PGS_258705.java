/*
프로그래머스 / 산 모양 타일링 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/258705
 */
class PGS_258705 {

    public static final int MOD = 10007;

    public int solution(int n, int[] tops) {
        int total = 2 * n + 1;
        int[] dp = new int[total + 1];  //dp[i]: i번째까지 채우는 경우의 수

        dp[1] = 1;

        //2번째 삼각형 위에 삼각형 존재 여부
        if (tops[0] == 0) {
            dp[2] = 2;
        } else {
            dp[2] = 3;
        }

        //dp[i] = dp[i - 1] + dp[i - 2]
        //dp[i - 1] -> i번째를 삼각형으로 채우는 경우
        //dp[i - 2] -> i, i - 1번째를 마름모로 채우는 경우
        for (int i = 3; i <= total; i++) {
            if (i % 2 == 0) {  //짝수번째일 경우
                if (tops[i / 2 - 1] == 1) {  //위에 삼각형이 있으면 i번째에 삼각형으로 채우는 경우의 수의 2배
                    dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % MOD;
                } else {
                    dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
                }
            } else {
                dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
            }
        }

        return dp[total];
    }
}

