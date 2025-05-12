package pkl0912.pgs;

import java.util.*;
import java.util.Map.Entry;

public class PGS_42579 {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        ArrayList<Integer> answerList = new ArrayList<>();
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        
        for(int i=0; i<genres.length; i++) hm.put(genres[i], hm.getOrDefault(genres[i],0) + plays[i]);
        
        List<Entry<String, Integer>> genresList = new ArrayList<Entry<String, Integer>>(hm.entrySet());
        Collections.sort(genresList, new Comparator<Entry<String, Integer>>(){
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2){
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        
        for(Entry<String, Integer> entry : genresList){
            
            HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
            for(int i=0; i<genres.length; i++){
                if(entry.getKey().equals(genres[i])) temp.put(i, plays[i]);
            }
            
            List<Entry<Integer, Integer>> songsList = new ArrayList<Entry<Integer, Integer>>(temp.entrySet());
            Collections.sort(songsList, new Comparator<Entry<Integer, Integer>>(){
                public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2){
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            
             for(int j=0; j<songsList.size() && j<2; j++) answerList.add(songsList.get(j).getKey());
            
        }
        
        answer = new int[answerList.size()];
        for(int i=0; i<answerList.size(); i++) answer[i] = answerList.get(i);
        return answer;
    }
}

