package yeonjy.pgs;

import java.io.*;
import java.util.*;

public class PGS_258707 {
    static int[] g;
    static int find(int x){
        if (g[x] == x) return x;
        return g[x] = find(g[x]);
    }
  
    static boolean union(int x, int y){
        int a = find(x);
        int b = find(y);
        if (a == b) return false;
        else if (a > b)
            g[b] = a;
        else
            g[a] = b;
        return true;
    }
  
    static int binarySearch(int[] arr, int num){
        int l = 0;
        int r = arr.length - 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if (num >= arr[mid])
                l = mid + 1;
            else
                r = mid - 1;
        }
        return l;
    }
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[M];
        g = new int[M + 1];
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            g[i] = i;
        }
        g[M] = M;
        Arrays.sort(arr);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++){
            int num = Integer.parseInt(st.nextToken());
            int index = binarySearch(arr, num);
            index = find(index);
            sb.append(arr[index] + "\n");
            union(index, index + 1);
        }
        System.out.print(sb);
    }
}
