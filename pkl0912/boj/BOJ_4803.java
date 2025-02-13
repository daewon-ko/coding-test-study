package pkl0912.boj;

import java.io.*;
import java.util.*;

public class BOJ_4803 {
    static List<Integer>[] list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n==0 && m==0){
                break;
            }
            list =  new ArrayList[n+1];
            for(int i = 0; i<n+1; i++){
                list[i] = new ArrayList<>();
            }
            for(int i = 0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                list[b].add(a);
            }
            visited = new boolean[n+1];
            int answer = 0;
            for(int i = 1; i<n+1; i++){
                if(!visited[i]){
                    answer+= checkTree(i);
                }
            }
            if(answer==0) System.out.println("Case 3: No trees.");
            else if(answer==1) System.out.println("Case 2: There is one tree.");
            else if(answer==3) System.out.println("Case 1: A forest of 3 trees.");
        }
      
    }
    static int checkTree(int root){
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        int node = 0;
        int edge = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            if(visited[cur]) continue;
            visited[cur] = true;
            node++;
            for(int next: list[cur]){
                edge++;
                if(!visited[next]){
                    q.add(next);
                }
            }
        }
        return (edge/2)+1 == node ? 1: 0;
    }
}
