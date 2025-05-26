package pkl0912.boj;

import java.util.*;
import java.io.*;

public class BOJ_1707 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        for(int i = 0; i<k ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            boolean answer = true;
            List<Integer>[] graph = new ArrayList[v+1];
            for(int j = 0; j<v+1; j++){
                graph[j] = new ArrayList<>();
            }
            for(int j = 0; j<e; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }
            int[] color = new int[v+1];
            boolean isBinary = true;
            for(int start = 1; start<=v; start++){
                if(color[start]!=0) continue;
                Queue<Integer> q = new LinkedList<>();
                q.add(start);
                color[start] = 1;

                while(!q.isEmpty() && isBinary){
                    int cur = q.poll();
                    for(int next: graph[cur]){
                        if(color[next]==0){
                            color[next] = color[cur]*(-1);
                            q.add(next);
                        }else{
                            if(color[next]==color[cur]){
                                isBinary = false;
                                break;
                            }
                        }
                    }
                }
                if(!isBinary) break;
            }
            System.out.println(isBinary ? "YES" : "NO");
        }
        
    }

    
}
