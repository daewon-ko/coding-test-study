package pkl0912.boj;
import java.util.*;
import java.io.*;

public class BOJ_1174 {
    static int n;
    static Set<Long> set;
    static int[] nums;
    public static void main(String[]args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        nums = new int[]{9,8,7,6,5,4,3,2,1,0};

        set = new HashSet<>();
        dfs(0,0);
        List<Long> list = new ArrayList<>(set);
        Collections.sort(list);
        if(n > list.size()) System.out.println(-1);
        else System.out.println(list.get(n-1));

    }
    static void dfs(long num, int idx){
        set.add(num);
        if(idx>=10){
            return;
        }
        dfs(10*num+nums[idx], idx+1);
        dfs(num, idx+1);
    }
}
