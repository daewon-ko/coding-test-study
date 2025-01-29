package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
백준 / 이차원 배열과 연산 / 골드4
https://www.acmicpc.net/problem/17140
 */
public class BOJ_17140 {
    static int r, c, k;
    static List<List<Integer>> rows = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        //초기 입력값
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                row.add(Integer.parseInt(st.nextToken()));
            }
            rows.add(row);
        }

        int time = 0;
        while (time <= 100) {
            int rowSize = rows.size();
            int colSize = rows.get(0).size();

            //문제 조건에 해당되면 정답 출력
            if (r <= rowSize && c <= colSize) {
                if (rows.get(r - 1).get(c - 1) == k) {
                    System.out.println(time);
                    return;
                }
            }

            //연산 수행
            if (rowSize >= colSize) {
                processR();
            } else {
                processC();
            }

            time++;
        }

        System.out.println(-1);  //100초 초과 시 -1 출력
    }

    private static void processR() {
        List<List<Integer>> newRows = new ArrayList<>();
        int maxColSize = 0;

        for (List<Integer> row : rows) {
            List<Integer> sortedRow = sortAndCount(row);
            newRows.add(sortedRow);
            maxColSize = Math.max(maxColSize, sortedRow.size());
        }

        //행 크기 맞추기
        for (List<Integer> row : newRows) {
            while (row.size() < maxColSize) {
                row.add(0);
            }
        }

        rows = newRows;
    }

    private static void processC() {
        int rowSize = rows.size();
        int colSize = rows.get(0).size();

        List<List<Integer>> newRows = new ArrayList<>();
        for (int i = 0; i < colSize; i++) {
            List<Integer> col = new ArrayList<>();
            for (int j = 0; j < rowSize; j++) {
                col.add(rows.get(j).get(i));
            }

            List<Integer> sortedCol = sortAndCount(col);
            newRows.add(sortedCol);
        }

        int maxRowSize = newRows.stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);

        //행 크기 맞추기
        for (List<Integer> row : newRows) {
            while (row.size() < maxRowSize) {
                row.add(0);
            }
        }

        //열 기준을 행 기준으로 변환
        List<List<Integer>> transposed = new ArrayList<>();
        for (int i = 0; i < maxRowSize; i++) {
            List<Integer> newRow = new ArrayList<>();
            for (List<Integer> row : newRows) {
                if (i < row.size()) {
                    newRow.add(row.get(i));
                } else {
                    newRow.add(0);
                }
            }

            transposed.add(newRow);
        }

        rows = transposed;
    }

    private static List<Integer> sortAndCount(List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : list) {  //숫자 카운트
            if (num != 0) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {  //등장 횟수가 같으면 수를 기준으로 정렬
                return o1.getKey() - o2.getKey();
            }
            return o1.getValue() - o2.getValue();
        });

        List<Integer> sortedList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : entries) {
            sortedList.add(entry.getKey());
            sortedList.add(entry.getValue());
        }

        return sortedList;
    }
}
