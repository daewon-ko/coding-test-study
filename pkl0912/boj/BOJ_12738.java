package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_12738 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
    
        List<Integer> list = new ArrayList<>();
        list.add(Integer.MIN_VALUE);
        for(int i = 0; i<n; i++){
            int num = arr[i];
            int lt = 1;
            int rt = list.size()-1;
            if(num>list.get(list.size()-1)) list.add(num);
            else{
                while(lt<rt){
                    int mid = (lt+rt) /2;
                    if(list.get(mid)>=num){
                        rt = mid;
                    }else{
                        lt = mid+1;
                    }
                }
                list.set(rt, num);
            }
            
        }
        System.out.println(list.size()-1);
        
    }
}
