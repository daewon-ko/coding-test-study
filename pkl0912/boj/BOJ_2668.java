package pkl0912.boj;
import java.util.*;
public class BOJ_2668 {
    static int n;
    static List<Integer>[] graph;
    static List<Integer> nums;
    static List<Integer> result = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        graph = new ArrayList[n+1];
        for(int i = 0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = sc.nextInt();
            graph[i+1].add(arr[i]);
        }
        for(int i = 1; i<=n; i++){
            dfs(i, i, new boolean[n+1]);
        }
        System.out.println(result.size());
        for(int r: result){
            System.out.println(r);
        }
        

    }
    static void dfs(int start, int cur, boolean[] visited){
        for(int child: graph[cur]){
            if(child==start){
                result.add(child);
                return;
            }
            if(!visited[child]){
                visited[child] = true;
                dfs(start, child, visited);
            }

        }
        return;
    }
}
