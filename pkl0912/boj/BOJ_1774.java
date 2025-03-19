package pkl0912.boj;
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class BOJ_1774 {
    static int[][] pos;
    static int n;
    static double[][] matrix;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        matrix = new double[n][n];
        pos = new int[n][2];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pos[i] = new int[]{x, y};
            for(int j = 0; j<i; j++){
                double d = calDist(x, pos[j][0], y, pos[j][1]);
                matrix[i][j] = matrix[j][i] = d;
            }
        }
        boolean[] visited = new boolean[n];
        double[] distance = new double[n];
        Arrays.fill(distance, Double.MAX_VALUE);
        distance[0] = 0;
        PriorityQueue<Minimum>pq = new PriorityQueue<>();
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            matrix[a][b] = matrix[b][a] = 0;
        }
        double answer = 0;
        pq.add(new Minimum(0, 0));
        int cnt = 1;
        while(!pq.isEmpty()){
            Minimum cur = pq.poll();
            if(visited[cur.idx]) continue;
            visited[cur.idx] = true;
            answer+= cur.dist;
            if(++cnt>n) break;
        
            for(int i = 0; i<n; i++){
                if(visited[i]) continue;
                if(matrix[cur.idx][i]<distance[i]){
                    distance[i] = matrix[cur.idx][i];
                    pq.add(new Minimum(i, distance[i]));
                }
            }
        }

        System.out.printf("%.2f", answer);


    }
    static double calDist(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x1-y1, 2)+Math.pow(x2-y2, 2));
    }
}
class Minimum implements Comparable<Minimum>{
    int idx;
    double dist;
    Minimum(int idx, double dist){
        this.idx = idx;
        this.dist = dist;
    }
    @Override
    public int compareTo(Minimum ob){
        return Double.compare(this.dist, ob.dist);
    }
}
