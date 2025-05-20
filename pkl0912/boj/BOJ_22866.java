package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_22866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] cnt = new int[n];
        int[] near = new int[n];
        Arrays.fill(near, Integer.MAX_VALUE);

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i<n; i++){
            while(!stack.isEmpty() && arr[stack.peek()]<=arr[i]){
                stack.pop();
            }
            cnt[i]+= stack.size();
            if(!stack.isEmpty()){
                near[i] = stack.peek();
            }
            stack.push(i);
        }
        stack.clear();
        for(int i = n-1; i>=0; i--){
            while(!stack.isEmpty() && arr[stack.peek()]<=arr[i]){
                stack.pop();
            }
            cnt[i]+= stack.size();
            if(!stack.isEmpty()){
                if(near[i]==Integer.MAX_VALUE || Math.abs(near[i]-i)>Math.abs(stack.peek()-i)){
                    near[i] = stack.peek();
                }
            }
            stack.push(i);
        }
        for(int i = 0; i<n; i++){
            if(cnt[i]==0) System.out.println(0);
            else{
                System.out.println(cnt[i]+" "+(near[i]+1));
            }
        }

        
    }
}
