package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_25401 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for(int i = 0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<n-1; i++){
            for(int j = i+1; j<n; j++){
                if((nums[j]- nums[i])%(j-i) != 0) continue;
                int d = (nums[j]- nums[i])/(j-i);
                int val = nums[i] - i*d;
                int changed = 0;
                for(int t =0; t<n; t++){
                    if(nums[t]!= val){
                        changed++;
                    }
                    val+=d;
                }
                min = Math.min(min, changed);
                
            }
        }
        System.out.println(min);
    }
}
