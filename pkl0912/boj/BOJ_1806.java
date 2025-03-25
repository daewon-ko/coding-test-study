package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int lt = 0;
        int total = 0;
        int answer = Integer.MAX_VALUE;
        boolean flag = false;
        for(int rt = 0; rt<n; rt++){
            total+=arr[rt];
            while(total>=s){
                answer = Math.min(answer, rt-lt+1);
                total-=arr[lt++];
            }
        }
        System.out.println(answer==Integer.MAX_VALUE?0:answer);
           
    }
    
}
