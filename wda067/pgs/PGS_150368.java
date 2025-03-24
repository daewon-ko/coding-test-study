/*
프로그래머스 / 이모티콘 할인행사 / Level 2
https://school.programmers.co.kr/learn/courses/30/lessons/150368
 */
class PGS_150368 {

    private final int[] discountRates = {10, 20, 30, 40};
    private int maxSubscribers = 0, maxRevenue = 0, emotionCount;
    private int[][] users;
    private int[] emotions;

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emotions = emoticons;
        this.emotionCount = emotions.length;
        int[] discounts = new int[emotionCount];  //이모티콘별 할인율

        recur(0, discounts);

        return new int[]{maxSubscribers, maxRevenue};
    }

    private void recur(int index, int[] discounts) {
        if (index == emotionCount) {  //이모티콘별 할인율 설정이 완료됐을 경우
            int subscribers = 0;  //구독자 수
            int revenue = 0;  //팬매액

            for (int[] user : users) {
                int discount = user[0];
                int price = user[1];
                int total = 0;

                for (int i = 0; i < emotionCount; i++) {  //모든 이모티콘에 대해 구매 결정
                    if (discounts[i] >= discount) {
                        total += emotions[i] * (100 - discounts[i]) / 100;
                    }
                }

                if (total >= price) {  //price 이상이면 구독
                    subscribers++;
                } else {
                    revenue += total;
                }
            }

            //구독자 수 갱신 또는 구독자 수는 같을 때 판매액 갱신
            if (subscribers > maxSubscribers || (subscribers == maxSubscribers && revenue > maxRevenue)) {
                maxSubscribers = subscribers;
                maxRevenue = revenue;
            }

            return;
        }

        for (int discountRate : discountRates) {  //모든 조합의 할인율에 재귀 호출
            discounts[index] = discountRate;
            recur(index + 1, discounts);
        }
    }
}

