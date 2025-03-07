package yeonjy.boj;

import java.util.*;
import java.io.*;

public class BOJ_2668 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] arr;
    static boolean[] check;
    static Set<Integer> s;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        check = new boolean[N+1];
        arr = new int[N+1];
        s = new TreeSet<>();
        for(int i = 1; i< = N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 1; i< = N; i++){
            check = new boolean[N+1];
            check[i] = true;
            dfs(i,i);
        }
        System.out.println(s.size());
        for(int i : s){
            System.out.println(i);
        }

    }
    static boolean dfs(int N, int first){
        int next = arr[N];
        if(!check[next]){
            check[next] = true;
            if(dfs(next, first)){
                s.add(N);
            }

        } else {
            if(next == first){
                s.add(N);
                return true;
            }
        }
        return false;
    }
}
