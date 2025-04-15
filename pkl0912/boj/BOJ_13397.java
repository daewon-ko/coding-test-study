package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_13397 {
    static int n;
    static int[] arr;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        int rt = -1;
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            rt = Math.max(arr[i], rt);
        }
        int lt = 0;
        while(lt<rt){
            int mid = (lt+rt) /2;
            if(solve(mid)<= m){
                rt = mid;
            }else{
                lt = mid+1;
            }

        }
        System.out.println(rt);
    }
    public static int solve(int mid){
        int min = INF;
        int max = -INF;
        int cnt = 1;
        for(int i = 0; i<n; i++){
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            if(max-min > mid){
                cnt++;
                i--;
                min = INF;
                max = -INF;
            }
        }
        return cnt;
    }
}