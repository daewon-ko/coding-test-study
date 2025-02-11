package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_15681 {
    static List<Integer>[] list, tree;
    static int[] parent, size;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        list = new ArrayList[n+1];
        tree = new ArrayList[n+1];
        parent = new int[n+1];
        size = new int[n+1];

        for(int i = 0; i<n+1; i++){
            list[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        makeTree(r, -1);
        countNode(r);
        for(int i = 0; i<q; i++){
            int u = Integer.parseInt(br.readLine());
            System.out.println(size[u]);
        }
    }
    static void makeTree(int curNode, int p){
        for(int node: list[curNode]){
            if(node != p){
                tree[curNode].add(node);
                parent[node] = curNode;
                makeTree(node, curNode);
            }
        }
    }
    static void countNode(int curNode){
        size[curNode] = 1;
        for(int node: tree[curNode]){
            countNode(node);
            size[curNode] +=size[node];
        }
    }
}
