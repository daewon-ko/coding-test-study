import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
프로그래머스 / 길 찾기 게임 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/42892
 */
class PGS_42892 {

    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }

        Arrays.sort(nodes, (a, b) -> {
            if (a.y == b.y) {
                return a.x - b.x;
            }
            return b.y - a.y;
        });

        Node root = nodes[0];

        for (int i = 1; i < n; i++) {
            insertNode(root, nodes[i]);
        }

        List<Integer> preorderList = new ArrayList<>();
        List<Integer> postorderList = new ArrayList<>();

        preorder(root, preorderList);
        postorder(root, postorderList);

        int[][] answer = new int[2][n];

        for (int i = 0; i < n; i++) {
            answer[0][i] = preorderList.get(i);
            answer[1][i] = postorderList.get(i);
        }

        return answer;
    }

    void insertNode(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }

    void preorder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.index);
        preorder(node.left, list);
        preorder(node.right, list);
    }

    void postorder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.index);
    }

    private static class Node {
        int x, y, index;
        Node left, right;

        Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }
}
