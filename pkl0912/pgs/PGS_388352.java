package pkl0912.pgs;

import java.util.*;

public class PGS_388352{
    int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {
        dfs(n, 1, 0, new ArrayList<>(), q, ans);
        return answer;
    }
    public void dfs(int n,int start, int cnt, List<Integer>selected, int[][]q, int[] ans){
        if(cnt==5){
            boolean flag = true;
            for(int i = 0; i<q.length; i++){
                int found = 0;
                for(int j = 0; j<5; j++){
                    if(selected.contains(q[i][j])) found++;
                }
                if(found!=ans[i]) flag = false;
            }
            if(flag) answer++;
            return;
        }
        for(int i = start; i<=n; i++){
            if(!selected.contains(i)){
                selected.add(i);
                dfs(n, i+1, cnt+1, selected, q, ans);
                selected.remove(selected.size()-1);
            }
        }
    }
}