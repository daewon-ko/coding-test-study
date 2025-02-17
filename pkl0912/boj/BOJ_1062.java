package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_1062 {
    static int n;
    static int k;
    static List<String> arr;
    static char[] alpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',  'n', 'o','p','q', 'r', 's', 't', 'u', 'v','w','x','y','z'};
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        for(int i = 0; i<n; i++){
            arr.add(br.readLine());
        }
        if(k<5){
            System.out.println(0);
            System.exit(0);
        }else{
            boolean visited[] = new boolean[26];
            visited['a'-'a'] = true;
            visited['n'-'a'] = true;
            visited['t'-'a'] = true;
            visited['i'-'a'] = true;
            visited['c'-'a'] = true;
            dfs(0,5,visited);
            System.out.println(max);
        }

        
        
    }
    static void dfs(int start, int cnt, boolean visited[]){
        if(cnt==k){

            int count = 0;
            for(String s: arr){
                boolean canRead = true;
                for(int i = 0; i<s.length();i++){
                    if(!visited[s.charAt(i)-'a']){
                        canRead = false;
                        break;
                    }
                }
                if(canRead) count++;
            }
            max = Math.max(max, count);
            return;
        }
        for(int i = start; i<26; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i+1, cnt+1, visited);
                visited[i] = false;
            }
            
        }
    }

}
