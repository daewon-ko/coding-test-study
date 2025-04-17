package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_1043 {
    static int[] parent;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // 진실을 아는 사람들 입력
        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());

        Set<Integer> truthRoots = new HashSet<>();
        List<Integer> truthPeople = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            int person = Integer.parseInt(st.nextToken());
            truthPeople.add(person);
        }

        List<Integer>[] partyList = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            partyList[i] = new ArrayList<>();
            int first = Integer.parseInt(st.nextToken());
            partyList[i].add(first);
            for (int j = 1; j < num; j++) {
                int next = Integer.parseInt(st.nextToken());
                partyList[i].add(next);
                union(first, next); // 파티 사람끼리 연결
            }
        }

        for (int person : truthPeople) {
            truthRoots.add(find(person));
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            boolean canLie = true;
            for (int p : partyList[i]) {
                if (truthRoots.contains(find(p))) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) count++;
        }

        System.out.println(count);
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int nx = find(x);
        int ny = find(y);
        if (nx != ny) parent[ny] = nx;
    }
}
