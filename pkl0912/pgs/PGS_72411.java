package pkl0912.pgs;

import java.util.*;
public class PGS_72411 {
    static Map<String, Integer> map;
    static int max;
    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();
        for(int c: course){
            map = new HashMap<>();
            max = 0;
            for(String order: orders){
                char[] carr = order.toCharArray();
                Arrays.sort(carr);
                order = new String(carr);
                dfs(0,order, c, new StringBuilder());
            }
            for(String s: map.keySet()){
                if(map.get(s)>=2 && map.get(s)==max) result.add(s);
            }
        }
        Collections.sort(result);
        return result.toArray(new String[result.size()]);
    }
    static void dfs(int start, String order, int c, StringBuilder sb){
        if(sb.length()==c){
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0)+1);
            max = Math.max(max, map.get(sb.toString()));
            return;
        }
        for(int i = start; i<order.length(); i++){
            sb.append(order.charAt(i));
            dfs(i+1, order, c, sb);
            sb.setLength(sb.length()-1);
        }
    }
}
