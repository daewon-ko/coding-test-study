import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
프로그래머스 / 베스트앨범 / Level 3
https://school.programmers.co.kr/learn/courses/30/lessons/42579
 */
class PGS_42579 {

    public List<Integer> solution(String[] genres, int[] plays) {
        int len = genres.length;
        Map<String, Integer> totalPlays = new HashMap<>();  //장르별 총 재생 횟수
        Map<String, List<Song>> genreToSongs = new HashMap<>();  //장르 -> 노래 리스트

        for (int i = 0; i < len; i++) {
            String g = genres[i];
            int p = plays[i];

            totalPlays.put(g, totalPlays.getOrDefault(g, 0) + p);
            genreToSongs.computeIfAbsent(g, k -> new ArrayList<>())
                    .add(new Song(i, p));
        }

        List<String> sorted = new ArrayList<>(totalPlays.keySet());
        sorted.sort(Comparator.comparingInt(totalPlays::get).reversed());  //재생 횟수를 기준으로 장르 정렬

        List<Integer> result = new ArrayList<>();

        for (String g : sorted) {
            List<Song> songs = genreToSongs.get(g);
            songs.sort(Comparator.comparingInt((Song s) -> s.plays).reversed()  //재생 횟수 내림차순
                    .thenComparingInt(s -> s.id));  //고유 번호 오름차순

            int count = 0;
            for (Song song : songs) {
                if (count >= 2) {  //최대 2개까지 저장
                    break;
                }
                result.add(song.id);
                count++;
            }
        }

        return result;
    }

    private static class Song {
        int id, plays;

        Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
    }
}
