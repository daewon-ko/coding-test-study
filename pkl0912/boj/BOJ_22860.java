package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_22860 {
    static Map<String, Integer>isFolderMap;
    static Map<String, List<String>> treeMap;
    public static void main(String[]args)throws IOException{
        isFolderMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        treeMap = new HashMap<>();

        for(int i = 0; i<n+m; i++){
            st = new StringTokenizer(br.readLine());
            String folder = st.nextToken();
            String next = st.nextToken();
            int isFolder = Integer.parseInt(st.nextToken());
            treeMap.putIfAbsent(folder, new ArrayList<>());
            treeMap.putIfAbsent(next, new ArrayList<>());
            treeMap.get(folder).add(next);
            isFolderMap.put(folder, 1);
            isFolderMap.put(next, isFolder);
        }
        int q = Integer.parseInt(br.readLine());
        for(int i = 0; i<q; i++){
            String[] parts = br.readLine().split("/");
            String last = parts[parts.length-1];
            solve(last);
        }

    }
    static void solve(String start){
        Queue<String> q = new LinkedList<>();
        Map<String, Integer> answer = new HashMap<>(); 
        q.add(start);
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            String node = q.poll();
            if(isFolderMap.get(node)==null){
                System.out.print(0);
                System.out.print(" ");
                System.out.println(0);
                return;

            }else if(isFolderMap.get(node)==0){ //파일
                cnt++;
                answer.put(node, answer.getOrDefault(node, 0)+1);
            }else{ //폴더
                for(String child:treeMap.get(node)){
                    q.add(child);
                }
            }
            
        }
        System.out.print(answer.size());
        System.out.print(" ");
        System.out.println(cnt);
        
    }
    
}
