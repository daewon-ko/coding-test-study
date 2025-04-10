package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_22862 {
    static int[] arr;
    static int k;
    static int max = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int lt = 0;
        int rt = 0;
        int oddCnt = 0;
        while(rt<n){
            if(arr[rt]%2==1) oddCnt++;
            while(oddCnt>k){
                if(arr[lt]%2==1) oddCnt--;
                lt++;
            }
            max = Math.max(max, rt-lt+1-oddCnt);
            rt++;
        }
        System.out.println(max);
        
        
    }
    
}
