package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_2141{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long total = 0;
        List<House> arr = new ArrayList<>();
        for(int i = 0; i<n; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long a = Integer.parseInt(st.nextToken());
            total+=a;
            arr.add(new House(x, a));
        }
        long cur = 0;
        Collections.sort(arr);
        for(House h: arr){
            long cnt = h.people;
            cur+= cnt;
            if(cur>=(total+1)/2){
                System.out.println(h.pos);
                System.exit(0);
            }

        }
        
    }
}
class House implements Comparable<House>{
    long pos;
    long people;
    House(long pos, long people){
        this.pos = pos;
        this.people = people;
    }
    @Override
    public int compareTo(House ob){
        return (int)(this.pos-ob.pos);
    }
}