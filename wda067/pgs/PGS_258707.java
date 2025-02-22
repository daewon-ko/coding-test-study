import java.util.ArrayList;
import java.util.List;

/*
프로그래머스 / n + 1 카드게임 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/258707
 */
class PGS_258707 {

    private static List<Integer> cardList = new ArrayList<>();
    private static boolean[] paired;
    private static int n, myCoin;

    public int solution(int coin, int[] cards) {
        n = cards.length;
        myCoin = coin;
        paired = new boolean[n];

        //처음 카드 뽑기
        for (int i = 0; i < n / 3; i++) {
            cardList.add(cards[i]);
        }

        int round = 1;

        //라운드 진행
        for (int i = n / 3; i < n; i += 2) {
            cardList.add(cards[i]);
            cardList.add(cards[i + 1]);

            if (!canPlay()) {  //n+1이 되는 카드쌍이 존재하지 않으면 게임 종료
                return round;
            }

            if (myCoin < 0) {  //동전이 0보다 작아지면 종료
                return round;
            }

            round++;
        }

        return round;
    }

    private static boolean canPlay() {
        //카드를 뽑기 전 합이 n+1이 되는 카드쌍 존재 여부
        for (int i = 0; i < n / 3; i++) {
            for (int j = i + 1; j < n / 3; j++) {
                if (cardList.get(i) + cardList.get(j) == n + 1 && !paired[i] && !paired[j]) {
                    paired[i] = true;
                    paired[j] = true;
                    return true;
                }
            }
        }

        //뽑은 카드를 포함하여 합이 n+1이 되는 카드쌍 존재 여부
        for (int i = 0; i <= cardList.size(); i++) {
            for (int j = i + 1; j < cardList.size(); j++) {
                if (cardList.get(i) + cardList.get(j) == n + 1 && !paired[i] && !paired[j]) {
                    paired[i] = true;
                    paired[j] = true;

                    //뽑은 카드일 경우 동전 소모
                    if (i >= n / 3) {
                        myCoin--;
                    }
                    if (j >= n / 3) {
                        myCoin--;
                    }

                    return true;
                }
            }
        }

        return false;  //존재하지 않으면 게임 종료
    }
}
