package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_4781 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(!(s=br.readLine()).equals("0 0.00")){
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            double m = Double.parseDouble(st.nextToken());
            int money = (int)m*100;
            int[] calory = new int[n];
            int[] price = new int[n];
            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                calory[i] = Integer.parseInt(st.nextToken());
                price[i] = (int)(Double.parseDouble(st.nextToken()) *100);
            }
            int[] dp = new int[money+1];
            for(int i = 0; i<n; i++){
                for(int j =  price[i]; j<money+1; j++){
                    dp[j] = Math.max(dp[j], dp[j-price[i]]+calory[i]);
                }
            
            }
            System.out.println(dp[money]);
        }
        
    }
    
}
