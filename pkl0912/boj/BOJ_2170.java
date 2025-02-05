package pkl0912.boj;
import java.io.*;
import java.util.*;

public class BOJ_2170 {
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Line> q = new PriorityQueue<>();

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            q.add(new Line(a, b));
        }
        Line first = q.poll();
        long answer = 0;
        long lstart = first.start;
        long lend = first.end;
        while(!q.isEmpty()){
            Line cur = q.poll();
            long s = cur.start;
            long e = cur.end;

            if(s<=lend && e>lend){//시작은 전에 포함되는데 끝이 더 길 때
                lend = e;
            }else if(s>lend){//시작이 끝보다 클 때
                answer += (lend-lstart);
                lstart = s;
                lend = e;
            }

        }
        answer+= (lend-lstart);
        System.out.println(answer);

    }
    
}
class Line implements Comparable<Line>{
    long start;
    long end;
    Line(long start, long end){
        this.start = start;
        this.end = end;
    }
    public int compareTo(Line ob){
        return (int)(this.start - ob.start);
    }
}