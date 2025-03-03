package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_2473 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] result = new int[3];
        Arrays.sort(arr);
        long min = Long.MAX_VALUE;
        for(int i = 0; i<n-2; i++){
            int left = i+1;
            int right = n-1;
            while(left<right){
                long sum = (long) arr[i] + arr[left] + arr[right];
                if(Math.abs(sum)<min){
                    min = Math.abs(sum);
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];

                }
                if(sum>0){
                    right--;
                }else{
                    left++;
                }
            }

        }
        System.out.println(result[0]+ " "+result[1]+" "+result[2]);

        
    }
    
}
