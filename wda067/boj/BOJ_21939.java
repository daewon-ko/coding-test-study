import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/*
백준 / 문제 추천 시스템 Version 1 / 골드4
https://www.acmicpc.net/problem/21939
 */
public class BOJ_21939 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        TreeMap<Integer, TreeSet<Integer>> problems = new TreeMap<>();  //난이도 -> 문제 번호
        Map<Integer, Integer> problemToLevel = new HashMap<>();  //문제 번호 -> 난이도

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());  //문제 번호
            int L = Integer.parseInt(st.nextToken());  //난이도

            problems.putIfAbsent(L, new TreeSet<>());
            problems.get(L).add(P);
            problemToLevel.put(P, L);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("recommend")) {
                int x = Integer.parseInt(st.nextToken());

                if (x == 1) {  //어려운 문제
                    int hardest = problems.lastKey();  //가장 높은 난이도
                    int last = problems.get(hardest).last();  //중에서 가장 큰 문제 번호
                    sb.append(last).append("\n");
                } else {  //쉬운 문제
                    int easiest = problems.firstKey();  //가장 낮은 난이도
                    int first = problems.get(easiest).first();  //중에서 가장 작은 문제 번호
                    sb.append(first).append("\n");
                }
            } else if (command.equals("add")) {  //문제 추가
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());

                problems.putIfAbsent(L, new TreeSet<>());
                problems.get(L).add(P);
                problemToLevel.put(P, L);
            } else {  //문제 제거
                int P = Integer.parseInt(st.nextToken());
                int L = problemToLevel.get(P);  //주어진 문제의 난이도

                TreeSet<Integer> targetProblems = problems.get(L);
                targetProblems.remove(P);
                if (targetProblems.isEmpty()) {  //해당 난이도의 문제가 존재하지 않을 경우
                    problems.remove(L);
                }
                problemToLevel.remove(P);
            }
        }

        System.out.println(sb);
    }
}
