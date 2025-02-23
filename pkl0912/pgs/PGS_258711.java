package pkl0912.pgs;
import java.util.*;

class PGS_258711 {
    static int donutCnt =0, stickCnt =0, eightCnt =0, circle = 0;
    static int [][] inOut;
    static int N;
    static HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
    public static int[] solution(int[][] edges) {
        

        for(int i =0; i<edges.length;i++){
            if(map.get(edges[i][0])!=null){
                ArrayList<Integer> result = map.get(edges[i][0]);
                result.add(edges[i][1]);
                map.put(edges[i][0],result);
            }
            else{
                ArrayList<Integer> result =new ArrayList<>();
                result.add(edges[i][1]);
                map.put(edges[i][0], result);
            }
                
        }
        HashSet<Integer> nodes = new HashSet<>();
        for(int i =0;i<edges.length;i++){
            nodes.add(edges[i][0]);
            nodes.add(edges[i][1]);
        }
        N = nodes.size();
        inOut = new int [N+1][2];
        for(int i =0;i<edges.length;i++){
            inOut[edges[i][0]][0] ++;
            inOut[edges[i][1]][1] ++;
            
        }
        for(int i=0;i<inOut.length;i++){
            if(inOut[i][1]==0 &&inOut[i][0]>1) circle = i;
        }
        inOut[circle][1]= -1;
        inOut[circle][0] = -1;
        
        for(int i=0;i<inOut.length;i++){
            if(inOut[i][0]==2) eightCnt++;
            else if(inOut[i][1]>0 && inOut[i][0]==0) stickCnt++;
        }
        donutCnt = map.get(circle).size()-eightCnt-stickCnt;
        int[] answer = {circle, donutCnt, stickCnt, eightCnt};
        return answer;
    }
}