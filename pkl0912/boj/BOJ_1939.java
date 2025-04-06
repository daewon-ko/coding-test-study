package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_1939 {
    static int n;
    static int m;
    static List<int[]>[] arr;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n+1];
        for(int i = 0; i<n+1; i++){
            arr[i] = new ArrayList<>();
        }
        

        for(int i = 0; i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new int[]{b, c});
            arr[b].add(new int[]{a, c});
            max = Math.max(max, c);

        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int lt =1;
        int rt = max;
        int result = 0;
        while(lt<=rt){
            int mid = (lt+rt) /2;
            if(bfs(start, end, mid)){
                result = mid;
                lt = mid+1;
            }else{
                rt = mid-1;
            }
        }
        System.out.println(result);

    }
    static boolean bfs(int start, int end, int weight){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur==end) return true;
            for(int[] pos: arr[cur]){
                int next = pos[0];
                int w = pos[1];
                if(!visited[next] && w>=weight ){
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return false;
    }
}