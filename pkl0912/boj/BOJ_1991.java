package pkl0912.boj;
import java.util.*;
import java.io.*;
public class BOJ_1991 {
    static Node[] tree;
    public static void preorder(Node node){
        if(node == null) return;
        System.out.print(node.value);
        preorder(node.left);
        preorder(node.right);
    }
    public static void inorder(Node node){
        if(node == null) return;
        inorder(node.left);
        System.out.print(node.value);
        inorder(node.right);
    }
    public static void postorder(Node node){
        if(node==null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        tree = new Node[n+1];
        
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            if(tree[parent-'A']==null){
                tree[parent-'A'] = new Node(parent);
            }
            if(left!='.'){
                tree[left-'A'] = new Node(left);
                tree[parent-'A'].left = tree[left-'A'];
            }
            if(right!= '.'){
                tree[right-'A'] = new Node(right);
                tree[parent-'A'].right = tree[right-'A'];
            }
        }
        
        preorder(tree[0]);
        System.out.println();
        inorder(tree[0]);
        System.out.println();
        postorder(tree[0]);
        System.out.println();

    }


}
class Node{
    char value;
    Node left;
    Node right;
    public Node(char value){
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
