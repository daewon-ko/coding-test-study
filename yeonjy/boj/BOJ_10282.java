package yeonjy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Node implements  Comparable<Node>{
    int index;
    int distance;

    public Node(int index, int distance){
        this.index= index;
        this.distance= distance;
    }
    public Node(){

    }
    @Override
    public int compareTo(Node other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}
public class BOJ_10282 {
    static final int INF = 1000000001;
    static int TC;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        for(int t=0; t<TC; t++){
            String[] input =br.readLine().split(" ");
            int c= Integer.parseInt(input[0]);
            int d = Integer.parseInt(input[1]);
            int start = Integer.parseInt(input[2]);

            int[] dp = new int[c+1];
            Arrays.fill(dp,INF);
            ArrayList<ArrayList<Node>> computers= new ArrayList<>();
            for(int i=0; i<=c; i++){
                computers.add(new ArrayList<Node>());
            }
            for(int i=0; i<d; i++){
                input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
                int dis = Integer.parseInt(input[2]);
                computers.get(b).add(new Node(a,dis));
            }

            dijkstra(start, dp, computers);

            int count_com =0;
            int time=0;
            for(int i=0; i<dp.length; i++){
                if(dp[i]<INF){
                    count_com++;
                    if(time<dp[i]){
                        time = dp[i];
                    }
                }
            }
            System.out.println(count_com+" " + time);
        }
    }
    public static void dijkstra(int start, int[] dp, ArrayList<ArrayList<Node>> computers){
        PriorityQueue<Node> queue = new PriorityQueue<>();

        dp[start]=0;
        ArrayList<Node> node= computers.get(start);
        queue.add(new Node(start,0));

        while(!queue.isEmpty()){
            Node n= queue.poll();
            int idx = n.index;
            int dist = n.distance;
            if (dp[idx] < dist) continue;

            for(int i=0; i<computers.get(idx).size(); i++){
                Node next_n = computers.get(idx).get(i);
                int cost =dp[idx]+next_n.distance;
                if(cost<dp[next_n.index]){
                    dp[next_n.index] =cost;
                    queue.offer(new Node(next_n.index, cost));
                }
            }
        }
    }
    private static void print(ArrayList<ArrayList<Node>> computers) {
        for(int i = 0; i< computers.size(); i++){
            for(int j = 0; j< computers.get(i).size(); j++){
                System.out.println();
                System.out.println("i "+i);
                System.out.print(computers.get(i).get(j).index+" "+ computers.get(i).get(j).distance);
            }
        }
    }
}
