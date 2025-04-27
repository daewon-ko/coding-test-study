package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_1202 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        List<Jewel> jewels = new ArrayList<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(m, v));
        }
        int[] bags = new int[k];
        for(int i = 0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(bags);
        Collections.sort(jewels);

        int j = 0;
        long answer = 0;

        for(int i  =0; i<k; i++){
            while(true){
                if(j>=n) break;
                if(bags[i]<jewels.get(j).m) break;
                q.add(jewels.get(j).v);
                j++;
            }
            if(!q.isEmpty()){
                answer+=Math.abs(q.poll());
            }
        }
        System.out.println(answer);
        
    }   
}
class Jewel implements Comparable<Jewel>{
    int m;
    int v;
    Jewel(int m, int v){
        this.m = m;
        this.v = v;
    }
    @Override
    public int compareTo(Jewel ob){
        return this.m-ob.m;
    }
}
