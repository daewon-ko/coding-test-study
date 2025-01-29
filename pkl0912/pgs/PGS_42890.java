package pkl0912.pgs;
import java.util.*;
public class PGS_42890{
class Solution {
    static List<Integer> indexes;
    static List<Set<Integer>> candidates;
    static int cnt;
    public int solution(String[][] relation) {
        cnt = 0;
        indexes = new ArrayList<>();
        candidates = new ArrayList<>();
        for(int i = 0; i<relation[0].length; i++){
            indexes.add(i);
        }
        for(int k = 1; k<=relation[0].length; k++){
            dfs(0,k, new ArrayList<>(), relation);
        }
        return cnt;
    }
    public static void dfs(int start, int k, List<Integer>selected, String[][] relation){
        if(selected.size()==k){
            if(isCandidate(selected, relation)&&isMinimal(selected, relation)){
                cnt++;
                candidates.add(new HashSet<>(selected));
            }
        }
        for(int i = start; i<indexes.size(); i++ ){
            selected.add(indexes.get(i));
            dfs(i+1, k, selected, relation);
            selected.remove(selected.size()-1);
        }
    }
    public static boolean isCandidate(List<Integer>selected, String[][] relation){
        Set<String> set = new HashSet<>();
        for(int i = 0; i<relation.length; i++){
            StringBuilder sb = new StringBuilder();
            for(int idx: selected){
                sb.append(relation[i][idx]).append(',');
            }
            set.add(sb.toString());
        }
        return set.size()==relation.length;
    }
    public static boolean isMinimal(List<Integer>selected, String[][]relation){
        for(Set<Integer>key: candidates){
            if(selected.containsAll(key)) return false;
        }
        return true;
    }
    
} 

    
}