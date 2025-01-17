package daewonko.boj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_20437 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt(); // 테스트 케이스 수
        sc.nextLine(); // 버퍼 비우기

        while (T-- > 0) {
            String W = sc.nextLine();
            int K = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            // 알파벳별 등장 인덱스 리스트를 저장하기 위한 자료구조
            List<Integer>[] positions = new List[52];
            for (int i = 0; i < 52; i++) {
                positions[i] = new ArrayList<>();
            }

            // 문자열 내의 각 문자 위치 저장
            // 대소문자가 섞여 있을 수 있으므로 구분
            // (예: 'A'~'Z'를 0~25, 'a'~'z'를 26~51 인덱스로 매핑)
            for (int i = 0; i < W.length(); i++) {
                char c = W.charAt(i);
                int idx;
                if ('A' <= c && c <= 'Z') {
                    idx = c - 'A';
                } else {
                    idx = c - 'a' + 26;
                }
                positions[idx].add(i);
            }

            // 결과값 초기화
            int minLen = Integer.MAX_VALUE;
            int maxLen = -1;

            // 각 문자별로 정확히 K번 등장하는 연속 구간 찾기
            for (int i = 0; i < 52; i++) {
                List<Integer> list = positions[i];

                // 만약 해당 문자가 K번 이상 등장하지 않았다면 건너뜀
                if (list.size() < K) {
                    continue;
                }

                // 연속하는 K개의 위치를 확인하면서 구간 길이 계산
                for (int start = 0; start <= list.size() - K; start++) {
                    int front = list.get(start);
                    int end = list.get(start + K - 1);
                    int length = end - front + 1;

                    minLen = Math.min(minLen, length);
                    maxLen = Math.max(maxLen, length);
                }
            }

            // 결과 출력
            if (maxLen == -1) {
                System.out.println(-1);
            } else {
                System.out.println(minLen + " " + maxLen);
            }
        }

        sc.close();
    }
}

