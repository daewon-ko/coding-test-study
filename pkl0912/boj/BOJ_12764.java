package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_12764{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n =  Integer.parseInt(br.readLine());
        PriorityQueue<Node> q = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            q.add(new Node(start, end, i));
        }
        PriorityQueue<EndNode> q2 = new PriorityQueue<>();
        PriorityQueue<Integer> availableSeats = new PriorityQueue<>();
        int cnt = 0;
        int[] personCnt = new int[n];
        while(!q.isEmpty()){
            Node cur = q.poll();
            int start = cur.start;
            int end = cur.end;
            int idx  = cur.idx;
            while(!q2.isEmpty() && q2.peek().end<=start){
                availableSeats.add(q2.poll().idx);
            }
            int seatIdx;
            if(!availableSeats.isEmpty()){
                seatIdx = availableSeats.poll();
            }else{
                seatIdx = cnt++;
            }
            personCnt[seatIdx]++;
            q2.add(new EndNode(end, seatIdx));
        }
        System.out.println(cnt);

        for(int i = 0; i<cnt; i++){
            System.out.print(personCnt[i]+" ");
        }
    }
}

class Node implements Comparable<Node>{
    int start;
    int end;
    int idx;
    Node(int start, int end, int idx){
        this.start = start;
        this.end = end;
        this.idx = idx;
    }
    @Override
    public int compareTo(Node ob){
        return this.start - ob.start;
    }

}

class EndNode implements Comparable<EndNode>{
    int end;
    int idx;
    EndNode(int end, int idx){
        this.end = end;
        this.idx = idx;
    }
    @Override
    public int compareTo(EndNode ob){
        return this.end - ob.end;
    }

}