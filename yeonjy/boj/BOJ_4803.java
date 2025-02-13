import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] graphs;
    static boolean[] isVisited;
    static boolean isCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int caseCnt = 0;

        while (true) {
            caseCnt++;
            int treeCnt = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0) {
                System.out.println(sb);
                return;
            }
            graphs = new List[n + 1];
            for (int i = 0; i <= n; i++) {
                graphs[i] = new ArrayList<>();
            }
            isVisited = new boolean[n + 1];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graphs[a].add(b);
                graphs[b].add(a);
            }

            for (int i = 1; i <= n; i++) {
                if (!isVisited[i]) {
                    isCycle = false;
                    isTree(i, 0);
                    if (!isCycle) {
                        treeCnt++;
                    }
                }
            }
            sb.append("Case " + caseCnt + ": ");
            if (treeCnt > 1) {
                sb.append("A forest of " + treeCnt + " trees.\n");
            } else if (treeCnt == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("No trees.\n");
            }
        }
    }

    static void isTree(int now, int pre) {
        isVisited[now] = true;
        for (int next : graphs[now]) {
            if (isVisited[next]) {
                if (next != pre) {
                    isCycle = true;
                }
            } else {
                isTree(next, now);
            }
        }
    }
}
