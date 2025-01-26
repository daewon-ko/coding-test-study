package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_2295 {
    public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        Set<Integer> sums = new HashSet<>();
        for(int i = 0; i<n-1; i++){
            for(int j = i+1; j<n; j++){
                sums.add(arr[i]+arr[j]);
            }
        }
        int answer = 0;
        
        for(int i = n-1; i>0; i--){
            for(int j = 0; j<i; j++){
                int z = arr[i]- arr[j];
                if(sums.contains(z)){
                    answer = arr[i];
                }
            }
            if(answer!=0) break;
        }   
        System.out.println(answer);
    }
}
