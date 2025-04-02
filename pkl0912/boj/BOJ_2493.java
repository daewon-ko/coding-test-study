package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_2493 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<int[]> stack = new Stack<>();
        int[] result = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n; i++){
            while(!stack.isEmpty() && stack.peek()[0]<arr[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                result[i] = 0;
            }else{
                result[i] = stack.peek()[1];
            }
            stack.push(new int[]{arr[i], i+1});
        }
        for(int a:result){
            System.out.print(a+" ");
        }

    }
    
}
