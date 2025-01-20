package daewonko.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21608 {
    static int n;
    static int[][] graph;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static Map<Integer, List<Integer>> favoriteStudentMap = new HashMap<>();
    static List<Integer> seatOrder = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n + 1][n + 1];

        // 입력 받기
        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            seatOrder.add(student);

            List<Integer> favoriteList = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                favoriteList.add(Integer.parseInt(st.nextToken()));
            }
            favoriteStudentMap.put(student, favoriteList);
        }

        // 각 학생을 순서대로 자리 배치
        for (int student : seatOrder) {
            seatStudent(student);
        }

        // 만족도 계산 및 출력
        System.out.println(calculateSatisfaction());
    }

    private static void seatStudent(int student) {
        List<Integer> favoriteList = favoriteStudentMap.get(student);

        int bestY = 0, bestX = 0;
        int maxFavoriteCount = -1;
        int maxEmptyCount = -1;

        // 모든 칸을 순회하며 최적의 자리 찾기
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= n; x++) {
                if (graph[y][x] != 0) continue; // 이미 차 있는 자리 건너뛰기

                int favoriteCount = 0, emptyCount = 0;

                // 인접 4칸 체크
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (inRange(ny, nx)) {
                        if (graph[ny][nx] == 0) emptyCount++;
                        else if (favoriteList.contains(graph[ny][nx])) favoriteCount++;
                    }
                }

                // 우선순위 비교
                if (favoriteCount > maxFavoriteCount ||
                        (favoriteCount == maxFavoriteCount && emptyCount > maxEmptyCount) ||
                        (favoriteCount == maxFavoriteCount && emptyCount == maxEmptyCount && y < bestY) ||
                        (favoriteCount == maxFavoriteCount && emptyCount == maxEmptyCount && y == bestY && x < bestX)) {
                    bestY = y;
                    bestX = x;
                    maxFavoriteCount = favoriteCount;
                    maxEmptyCount = emptyCount;
                }
            }
        }

        // 최적의 자리에 배치
        graph[bestY][bestX] = student;
    }

    private static int calculateSatisfaction() {
        int totalSatisfaction = 0;

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= n; x++) {
                int student = graph[y][x];
                List<Integer> favoriteList = favoriteStudentMap.get(student);
                int favoriteCount = 0;

                // 인접 4칸에 좋아하는 학생 수 체크
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (inRange(ny, nx) && favoriteList.contains(graph[ny][nx])) {
                        favoriteCount++;
                    }
                }

                // 만족도 계산
                if (favoriteCount == 1) totalSatisfaction += 1;
                else if (favoriteCount == 2) totalSatisfaction += 10;
                else if (favoriteCount == 3) totalSatisfaction += 100;
                else if (favoriteCount == 4) totalSatisfaction += 1000;
            }
        }

        return totalSatisfaction;
    }

    private static boolean inRange(int y, int x) {
        return y >= 1 && y <= n && x >= 1 && x <= n;
    }
}
