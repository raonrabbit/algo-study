import java.util.*;

class Solution {
    public static Map<String, PriorityQueue<String>> stations;
    public static Deque<String> answer;

    private void dfs(String cur) {
        PriorityQueue<String> next_stations = stations.get(cur);
        while (next_stations != null && !next_stations.isEmpty()) {
            String next_station = next_stations.poll();
            dfs(next_station);
        }

        answer.addFirst(cur);
    }

    public String[] solution(String[][] tickets) {
        stations = new HashMap<>();
        answer = new ArrayDeque<>();

        for (String[] t : tickets) {
            stations.computeIfAbsent(t[0], k -> new PriorityQueue<>()).offer(t[1]);
        }

        dfs("ICN");

        return answer.toArray(new String[0]);
    }
}