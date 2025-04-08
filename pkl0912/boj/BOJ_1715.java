package pkl0912.boj;

import java.util.*;
import java.io.*;
public class BOJ_1715 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            pq.add(arr[i]);
        }
        int answer = 0;

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            int sum = a + b;
            answer += sum;
            pq.add(sum);
        }
        System.out.println(answer);

    }
}
