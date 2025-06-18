import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
백준 / 소문난 칠공주 / 골드3
https://www.acmicpc.net/problem/1941
 */
public class BOJ_1941 {

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static char[][] students = new char[5][5];
    private static boolean[] selected = new boolean[25];
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            students[i] = br.readLine().toCharArray();
        }

        recur(0, 0, 0);

        System.out.println(answer);
    }

    private static void recur(int start, int depth, int dasomCount) {
        if (depth == 7) {
            //다솜파 학생이 4명 이상이고, 모든 학생이 인접해 있을 때 카운트
            if (dasomCount >= 4 && isAdjacent()) {
                answer++;
            }
            return;
        }

        //모든 학생에 대하여 탐색
        for (int i = start; i < 25; i++) {
            selected[i] = true;
            int r = i / 5;
            int c = i % 5;
            //다솜파 학생일 경우 카운트하여 재귀 호출
            if (students[r][c] == 'S') {
                recur(i + 1, depth + 1, dasomCount + 1);
            } else {
                recur(i + 1, depth + 1, dasomCount);
            }
            selected[i] = false;
        }
    }

    //선택된 학생들의 인접 여부 확인
    private static boolean isAdjacent() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        int count = 0;

        //첫번째로 선택된 학생 탐색
        for (int i = 0; i < 25; i++) {
            if (selected[i]) {
                queue.add(new int[]{i / 5, i % 5});
                visited[i / 5][i % 5] = true;
                break;
            }
        }

        //첫번째 학생의 인접 학생부터 BFS 탐색
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            count++;

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];

                if (nextR < 0 || nextR >= 5 || nextC < 0 || nextC >= 5) {
                    continue;
                }

                int index = nextR * 5 + nextC;
                //인접한 학생일 경우 큐에 추가
                if (!visited[nextR][nextC] && selected[index]) {
                    visited[nextR][nextC] = true;
                    queue.add(new int[]{nextR, nextC});
                }
            }
        }

        return count == 7;  //인접된 학생의 수가 7일 때 true 반환
    }
}
