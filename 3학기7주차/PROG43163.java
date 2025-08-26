import java.util.*;

class Solution {
    public static String[] words_arr;
    public static boolean[] visited;
    public static String target_word;
    public static int min_move;
    public static int word_count;
    public static boolean is_done;

    public static boolean isOneDiff(String s1, String s2) {
        char[] s1_array = s1.toCharArray();
        char[] s2_array = s2.toCharArray();

        int diff_count = 0;
        for(int i = 0; i < s1_array.length; i++){
            if(s1_array[i] != s2_array[i]){
                diff_count++;
                if(diff_count > 1) return false;
            }
        }

        return diff_count == 1;
    }

    public static void DFS(int cur_idx, int depth) {
        if(words_arr[cur_idx].equals(target_word)){
            min_move = Math.min(min_move, depth);
            is_done = true;
            return;
        }

        for(int i = 0; i < word_count; i++){
            if(visited[i] || cur_idx == i) continue;

            if(!isOneDiff(words_arr[cur_idx], words_arr[i])) continue;

            visited[i] = true;
            DFS(i, depth + 1);
            visited[i] = false;
        }
        System.out.println();
    }

    public int solution(String begin, String target, String[] words) {
        word_count = words.length;
        words_arr = new String[word_count + 1];
        target_word = target;
        min_move = Integer.MAX_VALUE;
        visited = new boolean[word_count + 1];
        is_done = false;

        for(int i = 0; i < word_count; i++){
            words_arr[i] = words[i];
        }

        words_arr[word_count] = begin;

        visited[word_count] = true;
        DFS(word_count, 0);
        return is_done ? min_move : 0;
    }
}