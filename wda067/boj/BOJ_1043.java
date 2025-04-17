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

    static int[] parents;
    static List<Integer> knownPeople;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  //사람의 수
        int M = Integer.parseInt(st.nextToken());  //파티의 수

        //부모 노드 초기화
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int knownCount = Integer.parseInt(st.nextToken());  //진실을 아는 사람 수
        knownPeople = new ArrayList<>();  //진실을 아는 사람 번호 리스트

        if (knownCount == 0) {
            System.out.println(M);
            return;
        } else {
            for (int i = 0; i < knownCount; i++) {
                knownPeople.add(Integer.parseInt(st.nextToken()));
            }
        }

        List<int[]> parties = new ArrayList<>();

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());
            int peopleCount = Integer.parseInt(st.nextToken());  //각 파티의 사람 수
            int[] party = new int[peopleCount];  //각 파티 배열
            int x = Integer.parseInt(st.nextToken());  //첫번째 사람
            party[0] = x;

            //첫번째 사람과 나머지 사람을 합침
            for (int j = 1; j < peopleCount; j++) {
                int y = Integer.parseInt(st.nextToken());
                unionParent(x, y);
                party[j] = y;
            }

            parties.add(party);
        }

        int count = 0;

        for (int[] party : parties) {
            for (int i : party) {
                int findParent = getParent(parents[i]);
                //파티 참가자의 부모 노드가 리스트에 포함될 때 카운트
                if (knownPeople.contains(findParent)) {
                    count++;
                    break;
                }
            }
        }

        System.out.println(M - count);
    }

    //부모 노드를 찾는 메서드
    static int getParent(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = getParent(parents[x]);
    }

    //두 부모 노드를 합치는 메서드
    static void unionParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        //진실을 아는 사람을 부모 노드로 하여 합친다.
        if (knownPeople.contains(b)) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }

}
