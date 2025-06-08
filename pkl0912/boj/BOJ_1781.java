package pkl0912.boj;

import java.util.*;
import java.io.*;

class HomeWork implements Comparable<HomeWork>{
    int deadline;
    int ramen;
    HomeWork(int deadline, int ramen){
        this.deadline = deadline;
        this.ramen = ramen;
    }

    @Override
    public int compareTo(HomeWork ob){
        
        return this.deadline - ob.deadline;
    }
}
public class BOJ_1781 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[n];
        List<HomeWork> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new HomeWork(a, b));
        }
        Collections.sort(list);
        int answer = 0;
        for(HomeWork hw: list){
            pq.add(hw.ramen);
            if(pq.size()>hw.deadline){
                pq.poll();
            }
        }

        while(!pq.isEmpty()){
            answer+=pq.poll();
        }
        System.out.println(answer);
        
    }
    
}
