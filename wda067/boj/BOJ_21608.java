import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 상어 초등학교 / 골드5
https://www.acmicpc.net/problem/21608
 */
public class BOJ_21608 {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static int N;
    private static int[][] classroom;
    private static List<List<Integer>> likedStudents = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        classroom = new int[N][N];
        Queue<Integer> queue = new LinkedList<>();  //학생의 순서

        for (int i = 0; i <= N * N; i++) {
            likedStudents.add(new ArrayList<>());
        }

        for (int i = 0; i < N * N; i++) {  //각 학생들의 좋아하는 학생의 번호 저장
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            queue.add(student);

            for (int j = 0; j < 4; j++) {
                int likedStudent = Integer.parseInt(st.nextToken());
                likedStudents.get(student).add(likedStudent);
            }
        }

        while (!queue.isEmpty()) {  //학생의 순서대로 자리 배치
            placeStudent(queue.poll());
        }

        System.out.println(calculateSatisfaction());
    }

    private static void placeStudent(int student) {
        int maxLike = -1;
        int maxEmpty = -1;
        int finalR = -1;
        int finalC = -1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (classroom[r][c] != 0) {  //빈자리가 아니면 스킵
                    continue;
                }

                int likeCount = 0;
                int emptyCount = 0;

                for (int dir = 0; dir < 4; dir++) {  //인접 자리 탐색
                    int nextR = r + DR[dir];
                    int nextC = c + DC[dir];

                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) {
                        continue;
                    }

                    int adjacentSeat = classroom[nextR][nextC];
                    if (likedStudents.get(student).contains(adjacentSeat)) {  //인접 자리에 좋아하는 학생이 앉았을 때
                        likeCount++;
                    } else if (adjacentSeat == 0) {  //인접 자리가 빈자리일 때
                        emptyCount++;
                    }
                }

                //현재 자리에 각 규칙을 순서대로 적용
                if (likeCount > maxLike) {  //좋아하는 학생 수가 더 많을 때
                    maxLike = likeCount;
                    maxEmpty = emptyCount;
                    finalR = r;
                    finalC = c;
                } else if (likeCount == maxLike) {  //좋아하는 학생 수가 같지만
                    if (emptyCount > maxEmpty) {  //빈자리가 더 많을 때
                        maxEmpty = emptyCount;
                        finalR = r;
                        finalC = c;
                    } else if (emptyCount == maxEmpty) {  //빈자리는 동일한데
                        if (r < finalR || (r == finalR && c < finalC)) {  //행과 열 번호가 더 작을 때
                            finalR = r;
                            finalC = c;
                        }
                    }
                }
            }
        }

        classroom[finalR][finalC] = student;
    }

    //모든 학생의 만족도 계산
    private static int calculateSatisfaction() {
        int satisfaction = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int student = classroom[r][c];
                int likeCount = 0;

                for (int dir = 0; dir < 4; dir++) {
                    int nextR = r + DR[dir];
                    int nextC = c + DC[dir];

                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) {
                        continue;
                    }

                    int adjacentSeat = classroom[nextR][nextC];
                    if (likedStudents.get(student).contains(adjacentSeat)) {
                        likeCount++;
                    }
                }

                if (likeCount > 0) {
                    satisfaction += (int) Math.pow(10, likeCount - 1);
                }
            }
        }

        return satisfaction;
    }
}
