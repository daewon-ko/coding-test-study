import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 / 줄 세우기 / 골드3
https://www.acmicpc.net/problem/2252
 */
public class BOJ_2252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  //학생 수
        int M = Integer.parseInt(st.nextToken());  //키를 비교한 횟수
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[N + 1];  //진입 차수 배열

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adjList.get(A).add(B);
            inDegree[B]++;
        }

        //진입 차수가 0인 노드부터 탐색
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            Integer curNode = queue.poll();
            sb.append(curNode).append(" ");
            //인접 노드를 탐색하면서 진입 차수--
            for (Integer adjNode : adjList.get(curNode)) {
                inDegree[adjNode]--;
                if (inDegree[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }

        System.out.println(sb);
    }
}
