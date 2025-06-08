package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_1111 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if(n==1 || (n==2 && arr[1]!=arr[0])){
            System.out.println("A");
            System.exit(0);
        }
        if(n==2 && arr[1]==arr[0]){
            System.out.println(arr[1]);
            System.exit(0);
        }
        int a = 0;
        int b = 0;
        int denom = arr[1]-arr[0];
        int numer = arr[2]-arr[1];
        if(denom==0){
            if(arr[2]!=arr[1]){
                System.out.println("B");
                return;
            }
            a = 0;
            b = arr[1];
        }else{
            if(numer%denom!=0){ 
                System.out.println("B");
                return;

            }else{
                a = numer/denom;
                b = arr[1] - a*arr[0];
            }
        }
        boolean isValid = true;
        for(int i = 1; i<n; i++){
            if(arr[i]!= (arr[i-1]*a+b)){
                isValid = false;
                break;
            }
        }
        if (isValid) {
            System.out.println(arr[n - 1] * a + b);
        } else {
            System.out.println("B");
        }
    }
}
