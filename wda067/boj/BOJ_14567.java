package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 선수과목 / 골드5
https://www.acmicpc.net/problem/14567
 */
public class BOJ_14567 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] result = new int[N + 1];
        int[] inDegree = new int[N + 1];
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());  //선수과목
            int B = Integer.parseInt(st.nextToken());
            adjList.get(A).add(B);
            inDegree[B]++;  //진입 차수 계산
        }

        Queue<Integer> queue = new LinkedList<>();
        //진입 차수가 0인 노드를 큐에 추가
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                result[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            for (Integer next : adjList.get(cur)) {  //인접 노드 탐색
                inDegree[next]--;  //진입 차수 -1

                if (inDegree[next] == 0) {  //진입 차수가 0이면 큐에 추가
                    queue.add(next);
                    result[next] = result[cur] + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);
    }
}
