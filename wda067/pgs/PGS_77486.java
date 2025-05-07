import java.util.HashMap;
import java.util.Map;

/*
프로그래머스 / 다단계 칫솔 판매 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/77486
 */
class PGS_77486 {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        Map<String, Integer> nameToIndex = new HashMap<>();  //이름 -> 인덱스 매핑
        int[] profits = new int[n];
        int[] parents = new int[n];

        for (int i = 0; i < n; i++) {  //매핑
            nameToIndex.put(enroll[i], i);
        }

        for (int i = 0; i < n; i++) {  //부모 설정
            if (referral[i].equals("-")) {  //부모가 없으면 -1
                parents[i] = -1;
            } else {
                parents[i] = nameToIndex.get(referral[i]);
            }
        }

        for (int i = 0; i < seller.length; i++) {
            int index = nameToIndex.get(seller[i]);  //판매원 인덱스
            int total = amount[i] * 100;  //개당 100원

            //부모가 없을때까지 or 커미션이 없을 때까지 반복
            while (index != -1 && total > 0) {
                int commission = (int) (total * 0.1);  //이익의 10%
                profits[index] += total - commission;  //커미션 제외 금액
                total = commission;  //이익 갱신
                index = parents[index];  //부모로 이동
            }
        }

        return profits;
    }
}
