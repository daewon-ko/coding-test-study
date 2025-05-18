package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_1300 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long low = 1;
        long hi = k;
    
        while(low<hi){
            long mid = (low+hi)/2;
            long count = 0;
            for(int i = 1; i<=n; i++){
                count+= Math.min(mid/i, n);
            }
            if(k<=count){
                hi = mid;
            }else{
                low = mid+1;
            }
        }
        System.out.println(low);
    }
}
