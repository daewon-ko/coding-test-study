/*
프로그래머스 / 표현 가능한 이진트리 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/150367
 */
public class PGS_150367 {

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);  //2진수로 변환
            int fullLength = getFullBinaryLength(binary.length());  //2진수로 만들 수 있는 포화 이진트리의 최대 노드 개수

            StringBuilder sb = new StringBuilder();
            int diff = fullLength - binary.length();
            for (int j = 0; j < diff; j++) {  //노드 수의 차이만큼 앞쪽에 0을 추가
                sb.append("0");
            }
            sb.append(binary);

            answer[i] = isValidBinaryTree(sb.toString()) ? 1 : 0;
        }
        return answer;
    }

    private int getFullBinaryLength(int length) {
        int fullLength = 1;
        while (fullLength < length) {  //1, 3, 7, 15, ...
            fullLength = fullLength * 2 + 1;
        }
        return fullLength;
    }

    private boolean isValidBinaryTree(String binary) {
        return checkSubTree(binary, 0, binary.length() - 1, false);
    }

    private boolean checkSubTree(String binary, int start, int end, boolean hasDummyParent) {
        if (start > end) {
            return true;
        }
        int mid = (start + end) / 2;
        boolean isDummy = binary.charAt(mid) == '0';
        if (hasDummyParent && !isDummy) {  //부모가 더미인데 자식는 더미가 아닐 경우
            return false;
        }

        //왼쪽 서브트리와 오른쪽 서브트리의 유효성 확인
        return checkSubTree(binary, start, mid - 1, isDummy) &&
                checkSubTree(binary, mid + 1, end, isDummy);
    }
}