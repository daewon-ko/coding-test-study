import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 / 거짓말 / 골드4
https://www.acmicpc.net/problem/1043
 */
public class BOJ_1043 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        List<Integer> truth = new ArrayList<>();  //진실을 아는 사람

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            int x = Integer.parseInt(st.nextToken());
            truth.add(x);
        }

        List<List<Integer>> parties = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int participant = Integer.parseInt(st.nextToken());

            List<Integer> party = new ArrayList<>();
            int first = Integer.parseInt(st.nextToken());
            party.add(first);

            if (participant > 1) {  //첫번째와 나머지 사람들을 union
                for (int j = 1; j < participant; j++) {
                    int rest = Integer.parseInt(st.nextToken());
                    party.add(rest);
                    union(first, rest);
                }
            }

            parties.add(party);
        }

        List<Integer> truthParent = new ArrayList<>();  //기존 진실을 아는 사람의 부모
        for (int person : truth) {
            truthParent.add(find(person));
        }

        int count = 0;
        for (List<Integer> party : parties) {
            boolean flag = false;

            for (Integer person : party) {
                int parent = find(person);
                if (truthParent.contains(parent)) {  //파티원의 부모가 진실을 알 경우
                    flag = true;
                }
            }

            if (!flag) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int first, int rest) {
        first = find(first);
        rest = find(rest);

        if (first != rest) {  //부모를 첫번째 파티원으로 설정
            parent[rest] = first;
        }
    }
}
