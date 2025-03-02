package pkl0912.boj;
import java.io.*;
import java.util.*;

public class BOJ_1365 {
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        dp = new int[n+1];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int len = 0;
        int idx = 0;
        for(int i = 0; i<n; i++){
            if(arr[i]>dp[len]){
                len++;
                dp[len] = arr[i];
                continue;
            }
            idx = binarySearch(0, len, arr[i]);
            dp[idx] = arr[i];
        }
        System.out.println(n-len);
        
    }
    static int binarySearch(int left, int right, int key){
        while(left<right){
            int mid = (left+right)/2;
            if(dp[mid]>key){
                right= mid;
            }else{
                left = mid+1;
            }
        }
        return right;
    }
}
