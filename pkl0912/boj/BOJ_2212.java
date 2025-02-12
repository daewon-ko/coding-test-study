package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_2212{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] minus = new int[n-1];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        for(int i = 1; i<n; i++){
            minus[i-1] = arr[i] - arr[i-1];
        }
        Arrays.sort(minus);
        int sum = 0;
        for(int i = 0; i<n-k; i++){
            sum+=minus[i];
        }
        System.out.println(sum);
    }
}