package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_21939 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<HProblem> hq = new PriorityQueue<>();
        PriorityQueue<EProblem> eq = new PriorityQueue<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int hard = Integer.parseInt(st.nextToken());
            hq.add(new HProblem(num, hard));
            eq.add(new EProblem(num, hard));
            map.computeIfAbsent(num,k->new HashSet<>()).add(hard);
        }
        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("add")){
                int num = Integer.parseInt(st.nextToken());
                int hard = Integer.parseInt(st.nextToken());
                hq.add(new HProblem(num, hard));
                eq.add(new EProblem(num, hard));
                map.computeIfAbsent(num,k->new HashSet<>()).add(hard);
            }else if(command.equals("recommend")){
                int a =  Integer.parseInt(st.nextToken());
                if(a==1){
                    while(!hq.isEmpty()){
                        HProblem top = hq.peek();
                        if(map.containsKey(top.num)&& map.get(top.num).contains(top.hard)){
                            sb.append(top.num).append("\n");
                            break;
                        }
                        hq.poll();
                    }
                }else{   
                    while(!eq.isEmpty()){
                        EProblem top = eq.peek();
                        if(map.containsKey(top.num)&& map.get(top.num).contains(top.hard)){
                            sb.append(top.num).append("\n");
                            break;
                        }
                        eq.poll();
                    }
                }
            }else{
                int num = Integer.parseInt(st.nextToken());
                map.remove(num);
            }
        }
        System.out.println(sb.toString());
        
    }
}
class HProblem implements Comparable<HProblem>{
    int num;
    int hard;
    HProblem(int num, int hard){
        this.num = num;
        this.hard = hard;
    }

    @Override
    public int compareTo(HProblem ob){
        if(this.hard==ob.hard) return ob.num - this.num;
        return ob.hard - this.hard;
    }
}

class EProblem implements Comparable<EProblem>{
    int num;
    int hard;
    EProblem(int num, int hard){
        this.num = num;
        this.hard = hard;
    }

    @Override
    public int compareTo(EProblem ob){
        if(this.hard==ob.hard) return this.num - ob.num;
        return this.hard - ob.hard;
    }
}
