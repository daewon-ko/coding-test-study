package pkl0912.boj;

import java.util.*;
import java.io.*;


public class BOJ_14267 {
    static int n;
    static int m;
    static int[] parent;
    static int[] dp;
    static List<Integer>[] tree;
    static List<int[]> praise;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        dp = new int[n+1];
        tree = new ArrayList[n+1];
        for(int i = 0; i<n+1; i++){
            tree[i] = new ArrayList<>();
        }
        for(int i = 1; i<=n; i++){
            int num = Integer.parseInt(st.nextToken());
            if(num!=-1){
                tree[num].add(i);
            }        
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dp[a]+= b;
        }
        dfs(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dp[i]).append(" ");
        }
        System.out.println(sb.toString());
        
    }
    static void dfs(int start){
        for(int child: tree[start]){
            dp[child]+=dp[start];
            dfs(child);
        }
    }
}
