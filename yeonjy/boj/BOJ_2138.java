package yeonjy.boj;

import java.io.*;
import java.util.*;

public class BOJ_2138 {
    
    private static int N, check1, check2;
    private static String start, end;
    private static int[] arr1, arr2, result;
    
    public static void main(String args[]) throws IOException {
      
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        start = br.readLine();
        end = br.readLine();
        
        arr1 = new int[N];
        arr2 = new int[N];
        result = new int[N];
        
        check1 = 0;
        check2 = 0;
        
        for(int i=0; i<N; i++){
            arr1[i] = start.charAt(i) - '0';
            arr2[i] = start.charAt(i) - '0';
            result[i] = end.charAt(i) - '0';
        }
        
        swap(arr2, 0);
        check2++;
        
        for(int i=1; i<N; i++){
            if(arr1[i-1] != result[i-1]){
                swap(arr1, i);
                check1++;
            }
            if(arr2[i-1] != result[i-1]){
                swap(arr2, i);
                check2++;
            }
        }
        
        if(check(arr1, result) && check(arr2, result)){
            check1 = (check1 < check2) ? check1 : check2;
            System.out.println(check1);
        } else if(check(arr1, result)){
            System.out.println(check1);
        } else if(check(arr2, result)){
            System.out.println(check2);
        } else{
            System.out.println(-1);
        }
        
        bw.flush();
        bw.close();
        
    }
    
    private static void swap(int[] arr, int i) {
        
        if(i > 0){
            if(arr[i-1] == 1) arr[i-1] = 0;
            else arr[i-1] = 1;
        }
        
        if(arr[i] == 1) arr[i] = 0;
        else arr[i] = 1;
        
        if(i == N-1) return;
        
        if(arr[i+1] == 1) arr[i+1] = 0;
        else arr[i+1] = 1;
        
        return;
    }
    
    private static boolean check(int[] a, int[] b){
        for(int i=0; i<N; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
    
}
