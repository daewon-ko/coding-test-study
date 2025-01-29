package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_17140 {
    static int[][] graph;
    static int row;
    static int col;
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        row = 3;
        col = 3;
        graph = new int[100][100];
        for(int i = 0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());     
            }
        }
        int time = 0;
        while(graph[r-1][c-1]!=k){
            if(time>100){
                System.out.println(-1);
                return;
            }
            if(row>=col) Rmove();
            else Cmove();
            time++;
        }
        System.out.println(time);

    }
    public static void Rmove(){
        int[][] newGraph = new int[100][100];
        int maxCol = 0;

        for(int i = 0; i<row; i++){
            
            Map<Integer, Integer> map = new HashMap<>();

            for(int j = 0; j<col; j++){
                int num = graph[i][j];
                if(num==0) continue;
                map.put(num, map.getOrDefault(num, 0)+1);
            }
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

            list.sort(Comparator
                .comparingInt(Map.Entry<Integer, Integer>::getValue) 
                .thenComparingInt(Map.Entry::getKey)); 

            int t = 0;
            for (Map.Entry<Integer, Integer> entry : list) {
                if(t>=100)break;
                newGraph[i][t++] = entry.getKey();
                newGraph[i][t++] = entry.getValue();
            }
            maxCol = Math.max(maxCol, t);
        }
        col = maxCol;
        graph = newGraph;

    }

    public static void Cmove(){
        int[][] newGraph = new int[100][100];
        int maxRow = 0;

        for(int j = 0; j<col; j++){
            
            Map<Integer, Integer> map = new HashMap<>();

            for(int i = 0; i<row; i++){
                int num = graph[i][j];
                if(num==0) continue;
                map.put(num, map.getOrDefault(num, 0)+1);
            }
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

            list.sort(Comparator
                .comparingInt(Map.Entry<Integer, Integer>::getValue) 
                .thenComparingInt(Map.Entry::getKey)); 
            int t = 0;
            for (Map.Entry<Integer, Integer> entry : list) {
                newGraph[t++][j] = entry.getKey();
                newGraph[t++][j] = entry.getValue();
            }
            maxRow = Math.max(row, t);
        }
        graph = newGraph;
        row = maxRow;


    }
}
