package pkl0912.boj;
import java.util.*;
import java.io.*;
import java.util.*;
import java.io.*;

public class BOJ_2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Lecture> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.add(new Lecture(a, b));
        }
        Collections.sort(arr);
        int max = 0;

        for(Lecture ob: arr){
            max = Math.max(max, ob.d);
        }
        boolean[] deadline = new boolean[max+1];
        int answer = 0;
        for(Lecture ob: arr){
            for(int i = ob.d; i>=1; i--){
                if(!deadline[i]){
                    deadline[i] = true;
                    answer+= ob.p;
                    break;
                }
            }
        }
        

        System.out.println(answer);
    }
}

class Lecture implements Comparable<Lecture> {
    int p;
    int d;

    public Lecture(int p, int d) {
        this.p = p;
        this.d = d;
    }

    @Override
    public int compareTo(Lecture ob) {
        return ob.p - this.p; 
    }
}

