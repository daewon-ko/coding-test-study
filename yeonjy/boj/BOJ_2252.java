package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] counts = new int[N + 1];

        ArrayList<ArrayList<Integer>> students = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            students.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());
            students.get(pre).add(post);
            counts[post]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (counts[i] == 0) {  // 앞에 학생 없을 때
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");

            for (int next : students.get(now)) {
                counts[next]--;  // 앞 학생이 줄을 섰기 때문에 빼줌
                if (counts[next] == 0) {
                    q.add(next);
                }
            }
        }
        System.out.println(sb);
    }
}
