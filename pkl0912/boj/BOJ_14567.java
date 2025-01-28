package pkl0912.boj;
import java.util.*;
import java.io.*;
public class BOJ_14567 {
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer>[] arr = new ArrayList[n+1];
        int[] dp = new int[n+1];
        for(int i = 0; i<n+1; i++){
            arr[i] = new ArrayList<>();
            dp[i] = 1;
        }
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
        }
        for(int i = 1; i<n+1; i++){
            if(arr[i].size()!=0){
                for(int next:arr[i]){
                    dp[next] = Math.max(dp[i]+1, dp[next]);
                }
            }
        }
        for(int i = 1; i<n+1; i++){
            System.out.println(dp[i]);
        }
        
    }
}
