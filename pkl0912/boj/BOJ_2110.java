package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_2110 {
    static int[] arr;
    static int max = Integer.MIN_VALUE;
    static int lastPlaced = 0;
    static int cnt = 2;
    static int n;
    static List<Integer> placed;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int lt = 1;
        int rt = arr[n-1] - arr[0];
        int answer = 0;
        while(lt<=rt){
            int mid = (lt+rt) /2;
            if(canBeDistance(mid, c)){
                answer = mid;
                lt = mid+1;
            }else{
                rt = mid-1;
            }

        }
        System.out.println(answer);


    }
    public static boolean canBeDistance(int dist, int c){
        int lastPlaced = arr[0];
        int count = 1;
        for(int i = 1; i<arr.length; i++){
            if(arr[i]-lastPlaced>=dist){
                count++;
                lastPlaced = arr[i];
                if(count>=c) return true;
            }
        }

        return false;
        
    }
}
