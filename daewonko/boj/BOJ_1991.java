package daewonko.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1991 {
    static int n;
    static Map<String, List<String>> tree = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
                String centerNode = st.nextToken();
                String leftNode = st.nextToken();
                String rightNode = st.nextToken();
                // Map의 Value의 0번째 값은 왼쪽노드, 1번째 값은 오른쪽 노드로 설정
                tree.put(centerNode, List.of(leftNode, rightNode));

        }


        // 전위
        // 변형된 DFS
        StringBuilder sb = new StringBuilder();
        preOrder(sb, "A");
        System.out.println(sb);


        // 중위 순회
        sb = new StringBuilder();
        inOrder(sb, "A");
        System.out.println(sb);

        //후위 순회
        sb = new StringBuilder();
        postOrder(sb, "A");
        System.out.println(sb);

    }

    public static void preOrder(StringBuilder sb, String node) {
        if (node.equals(".")) {
            return;
        }
        sb.append(node);    //  부모방문
        preOrder(sb, tree.get(node).get(0)); //왼쪽 방문
        preOrder(sb, tree.get(node).get(1)); //오른쪽 방문

    }

    public static void inOrder(StringBuilder sb, String node) {
        if (node.equals(".")) {
            return;
        }

        inOrder(sb, tree.get(node).get(0));  //왼쪽 노드 방문
        sb.append(node);    // 부모 방문
        inOrder(sb, tree.get(node).get(1));  //오른쪽 노드 방문

    }

    public static void postOrder(StringBuilder sb, String node) {
        if (node.equals(".")) {
            return;
        }

        postOrder(sb, tree.get(node).get(0)); //왼쪽노드
        postOrder(sb, tree.get(node).get(1));    // 오른쪽노드
        sb.append(node);

    }



}
