package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_2143 {
    public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] narr = new int[n];
        for(int i = 0; i<n; i++){   
            narr[i] = Integer.parseInt(st.nextToken());
        }
        Map<Integer, Integer> nap = new HashMap<>();
        for(int i  = 0;i<n; i++){
            int sum = 0;
            for(int j = i; j<n; j++){
                sum+= narr[j];
                nap.put(sum, nap.getOrDefault(sum, 0)+1);
            }
        }
        
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] marr = new int[m];
        for(int i = 0; i<m; i++){   
            marr[i] = Integer.parseInt(st.nextToken());
        }
        int answer =0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i  = 0;i<m; i++){
            int sum = 0;
            for(int j = i; j<m; j++){
                sum+= marr[j];
                int comp = t-sum;
                if(nap.containsKey(comp)){
                    answer+=nap.get(comp);
                }
            }
        }
        
        System.out.println(answer);
    }    
}
