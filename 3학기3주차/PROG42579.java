import java.util.*;
import java.util.stream.Collectors;

class Solution {
    List<Integer> answer = new ArrayList<>();

    public static class Genre implements Comparable<Genre> {
        public String genre;
        public PriorityQueue<Song> plays;
        public int totalPlays;

        public Genre(String genre){
            this.genre = genre;
            plays = new PriorityQueue<>();
        }

        public List<Integer> getSongs() {
            int returnCount = plays.size() >= 2 ? 2 : 1;

            List<Integer> result = new ArrayList<>();
            for(int i = 0; i < returnCount; i++){
                result.add(plays.poll().index);
            }

            return result;
        }

        @Override
        public int compareTo(Genre o){
            return o.totalPlays - this.totalPlays;
        }
    }

    public static class Song implements Comparable<Song> {
        public int index;
        public int play;

        public Song(int index, int play){
            this.index = index;
            this.play = play;
        }

        @Override
        public int compareTo(Song o){
            if(this.play != o.play) return o.play - this.play;
            return this.index - o.index;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        PriorityQueue<Genre> genres_pq = new PriorityQueue<>();
        Map<String, Genre> map = new HashMap<>();

        for(int i = 0; i < genres.length; i++){
            map.putIfAbsent(genres[i], new Genre(genres[i]));
            map.get(genres[i]).plays.add(new Song(i, plays[i]));
            map.get(genres[i]).totalPlays += plays[i];
        }

        for(Genre g : map.values()){
            genres_pq.add(g);
        }


        List<Integer> result = new ArrayList<>();
        while(!genres_pq.isEmpty()){
            Genre g = genres_pq.poll();
            List<Integer> temp = g.getSongs();
            for(int n : temp){
                result.add(n);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}