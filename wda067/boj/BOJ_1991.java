import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

/*
백준 / 트리 순회 / 실버1
https://www.acmicpc.net/problem/1991
 */
public class BOJ_1991 {

    private static int[][] binaryTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  //노드의 개수
        binaryTree = new int[26][2];

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            int parent = split[0].charAt(0) - 'A';
            char left = split[1].charAt(0);
            char right = split[2].charAt(0);

            if (left == '.') {
                binaryTree[parent][0] = -1;
            } else {
                binaryTree[parent][0] = left - 'A';
            }
            if (right == '.') {
                binaryTree[parent][1] = -1;
            } else {
                binaryTree[parent][1] = right - 'A';
            }
        }
        preorder(0);
        System.out.println();
        inorder(0);
        System.out.println();
        postorder(0);
    }

    private static void preorder(int node) {
        if (node == -1) {
            return;
        }
        System.out.print((char) (node + 'A'));
        preorder(binaryTree[node][0]);
        preorder(binaryTree[node][1]);
    }

    private static void inorder(int node) {
        if (node == -1) {
            return;
        }
        inorder(binaryTree[node][0]);
        System.out.print((char) (node + 'A'));
        inorder(binaryTree[node][1]);
    }

    private static void postorder(int node) {
        if (node == -1) {
            return;
        }
        postorder(binaryTree[node][0]);
        postorder(binaryTree[node][1]);
        System.out.print((char) (node + 'A'));
    }
}