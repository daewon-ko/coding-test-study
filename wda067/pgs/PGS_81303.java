import java.util.Stack;

/*
프로그래머스 / 표 편집 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/81303
 */
class PGS_81303 {

    public String solution(int n, int k, String[] cmd) {
        //잦은 행의 삭제와 복구 발생 -> 현재 위치 기준 위/아래 행을 빠르게 찾고 이동해야 함
        //각 행의 이전/다음 인덱스 저장 -> 연결 리스트
        int[] prev = new int[n];
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;  //마지막 행은 다음 행이 없음

        Stack<Integer> removed = new Stack<>();  //삭제 정보 저장
        boolean[] isRemoved = new boolean[n];  //삭제된 행

        for (String s : cmd) {
            char command = s.charAt(0);

            if (command == 'U') {
                int x = Integer.parseInt(s.substring(2));
                for (int i = 0; i < x; i++) {
                    k = prev[k];  //x번만큼 이전으로 이동
                }
            } else if (command == 'D') {
                int x = Integer.parseInt(s.substring(2));
                for (int i = 0; i < x; i++) {
                    k = next[k];  //x번만큼 다음으로 이동
                }
            } else if (command == 'C') {
                //k번 행 삭제
                removed.push(k);
                isRemoved[k] = true;

                if (prev[k] != -1) {  //첫번째 행이 아닐 경우
                    next[prev[k]] = next[k];  //k번 행의 이전 행과 다음행을 연결
                }
                if (next[k] != -1) {  //마지막 행이 아닐 경우
                    prev[next[k]] = prev[k];  //k번 행의 이전 행과 다음행을 연결
                }

                //새로운 k번 행이 마지막 행이 아니라면 포인터를 기존 k번 행의 다음 행으로 설정
                //마지막 행이라면 기존 k번 행의 이전 행으로 설정
                k = (next[k] != -1) ? next[k] : prev[k];
            } else if (command == 'Z') {
                int restored = removed.pop();
                isRemoved[restored] = false;

                if (prev[restored] != -1) {  //삭제된 행의 이전 행이 첫번째 행이 아닐 경우
                    next[prev[restored]] = restored;  //이전 행의 다음 행을 삭제된 행으로 설정
                }
                if (next[restored] != -1) {  //삭제된 행의 다음 행이 마지막 행이 아닐 경우
                    prev[next[restored]] = restored;  //다음 행의 이전 행을 삭제된 행으로 설정
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(isRemoved[i] ? 'X' : 'O');
        }

        return sb.toString();
    }
}
