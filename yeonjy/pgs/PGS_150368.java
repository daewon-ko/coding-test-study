package yeonjy.pgs;

import java.util.*;

class PGS_150368 {
    static final Map<Integer, Integer> percentMap = Map.of(10, 9, 20, 8, 30, 7, 40, 6);
    static final int[] pers = new int[]{10, 20, 30, 40};
    
    static int[][] users;
    static int[] emoticons;
    static int maxPlusUser;
    static long maxSale;
    
    public int[] solution(int[][] users, int[] emoticons) {        
        this.users = users;
        this.emoticons = emoticons;
        int[] percents = new int[emoticons.length];
        makePercents(0, percents);
        
        return new int[]{maxPlusUser, (int) maxSale};
    }
    
    private void makePercents(int cnt, int[] percents) {
        if (cnt == emoticons.length) {
            event(percents);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            percents[cnt] = pers[i];
            makePercents(cnt + 1, percents);
        }
    }
    
    private void event(int[] percents) {
        // 이모티콘 구매자
        List<Integer>[] emoticonBuyers = makeEmoticonBuyers(percents);
        
        // 할인된 이모티콘 가격
        int[] emoticonPrices = new int[percents.length];
        for(int i = 0; i < percents.length; i++) {
            emoticonPrices[i] = emoticons[i] / 10 * percentMap.get(percents[i]);
        }
        
        // 사용자별 구매 가격 계산 (이모티콘 플러스 고려 전)
        long[] totalPricesPerUsers = new long[users.length];
        for (int i = 0; i < percents.length; i++) {
            List<Integer> buyers = emoticonBuyers[i];
            for (int buyer : buyers) {
                totalPricesPerUsers[buyer] += emoticonPrices[i];
            }
        }
        
        // 이모티콘 플러스 가입자 수
        boolean[] isPlusUser = new boolean[users.length];
        int plusUserCnt = 0;
        for (int i = 0; i < users.length; i++) {
            if (totalPricesPerUsers[i] >= users[i][1]) {
                isPlusUser[i] = true;
                plusUserCnt++;
            }
        }
        if (maxPlusUser > plusUserCnt) {
            return;
        }
        
        // 이모티콘 플러스 고려하여 판매액 계산
        long totalPrice = 0L;
        for (int i = 0; i < users.length; i++) {
            if (!isPlusUser[i]) {
                totalPrice += totalPricesPerUsers[i];
            }
        }
        if (maxPlusUser < plusUserCnt) {
            maxPlusUser = plusUserCnt;
            maxSale = totalPrice;
        } else {  // maxPlusUser == plusUserCnt 인 경우
            maxSale = Math.max(maxSale, totalPrice);  
        }
    }
    
    static List<Integer>[] makeEmoticonBuyers(int[] percents) {
        List<Integer>[] emoticonBuyers = new ArrayList[percents.length];
        for (int i = 0; i < percents.length; i++) {
            int percent = percents[i];
            emoticonBuyers[i] = new ArrayList<>();
            for (int j = 0; j < users.length; j++) {
                if (percent >= users[j][0]) {
                    emoticonBuyers[i].add(j);
                }
            }
        }
        return emoticonBuyers;
    }
}
