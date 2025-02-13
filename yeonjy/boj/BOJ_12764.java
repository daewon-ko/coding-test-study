package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12764 {
    // 종료 시간 기준 후보
    static PriorityQueue<int[]> timeCandis = new PriorityQueue<>((c1, c2) -> c1[0] - c2[0]);  // 종료 시간, 좌석 번호
    // 테이블 번호 기준 후보
    static PriorityQueue<int[]> tableCandis = new PriorityQueue<>((c1, c2) -> c1[1] - c2[1]);  // 종료 시간, 좌석 번호

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        List<int[]> times = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            times.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        times.sort(Comparator.comparingInt(t -> t[0]));

        int idx = 0;
        int[] tables = new int[N];
        tables[idx]++;
        timeCandis.add(new int[]{times.get(0)[1], idx++});

        for (int i = 1; i < N; i++) {
            int[] now = times.get(i);
            checkEndTime(now[0]);

            if (!tableCandis.isEmpty()) {
                int[] candi = tableCandis.poll();
                timeCandis.add(new int[]{now[1], candi[1]});
                tables[candi[1]]++;
            } else {
                tables[idx]++;
                timeCandis.add(new int[]{now[1], idx});
                idx++;
            }
        }

        sb.append(idx).append("\n");
        for (int i = 0; i < idx; i++) {
            sb.append(tables[i]).append(" ");
        }
        System.out.println(sb);
    }

    static void checkEndTime(int now) {
        while (!timeCandis.isEmpty()) {
            int[] time = timeCandis.peek();
            if (time[0] < now) {
                tableCandis.add(time);
                timeCandis.poll();
            } else {
                break;
            }
        }
    }
}
