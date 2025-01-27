package pkl0912.boj;
import java.util.*;
import java.io.*;
public class BOJ_11000 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<Lect> pq = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            pq.add(new Lect(s,t));
        }

        PriorityQueue<Integer>ends = new PriorityQueue<>();
        while(!pq.isEmpty()){

                Lect l = pq.poll();
                if(!ends.isEmpty() && ends.peek()<=l.start){
                    ends.poll();
                }
                ends.add(l.end);

        }
        System.out.println(ends.size());


    }
}
class Lect implements Comparable<Lect>{
    int start;
    int end;
    public Lect(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    public int compareTo(Lect l){
        return this.start-l.start;
    }
}
