package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_7453 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] graph = new int[4][n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            graph[0][i] = Integer.parseInt(st.nextToken());
            graph[1][i] = Integer.parseInt(st.nextToken());
            graph[2][i] = Integer.parseInt(st.nextToken());
            graph[3][i] = Integer.parseInt(st.nextToken());
        }

        int[] ab = new int[n*n];
        int[] cd = new int[n*n];

        int idx = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                ab[idx++] = graph[0][i]+graph[1][j];
            }
        }
        idx = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                cd[idx++] = graph[2][i]+graph[3][j];
            }
        }
        Arrays.sort(ab);
        Arrays.sort(cd);

        int left = 0;
        int right = cd.length-1;
        int count = 0;

        while(left<ab.length && right>=0){
            int sum = ab[left]+cd[right];
            if(sum==0){
                int abVal = ab[left];
                int cdVal = cd[right];
                int abCnt = 0;
                int cdCnt = 0;
                while(left<ab.length && abVal == ab[left]){
                    abCnt++;
                    left++;
                }
                while(right>=0 && cdVal == cd[right]){
                    cdCnt++;
                    right--;
                }
                count+= abCnt*cdCnt;
            }else if(sum<0){
                left++;
            }else{
                right--;
            }
        }
        System.out.println(count);

    }
}
