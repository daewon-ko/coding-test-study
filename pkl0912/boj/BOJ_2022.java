package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_2022 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        double left = 0;
        double right = Math.min(x, y);
        while(right-left>=0.001){
            double width = (left+right)/2;
            double h1 = Math.sqrt(Math.pow(x, 2)-Math.pow(width,2));
            double h2 = Math.sqrt(Math.pow(y, 2)-Math.pow(width,2));
            double result = (h1*h2) / (h1+h2);
            if(result>=c) left = width;
            else right = width;
        }
        System.out.println(String.format("%.3f", right));
        
    }
}
