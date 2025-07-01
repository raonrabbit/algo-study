import java.util.*;

class Solution {
    private Map<Integer, List<Integer>> graph = new HashMap<>();
    private Map<Integer, Integer> in = new HashMap<>();
    private Map<Integer, Integer> out = new HashMap<>();
    private Set<Integer> visited = new HashSet<>();

    public int[] solution(int[][] edges) {
        graph.clear();
        in.clear();
        out.clear();
        visited.clear();

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            out.put(from, out.getOrDefault(from, 0) + 1);
            in.put(to, in.getOrDefault(to, 0) + 1);
        }

        int newNode = -1;
        for (int node : out.keySet()) {
            if (in.getOrDefault(node, 0) == 0 && out.get(node) >= 2) {
                newNode = node;
                break;
            }
        }

        int donut = 0, straight = 0, eight = 0;

        for (int start : graph.get(newNode)) {
            if (visited.contains(start)) continue;

            int result = find(start);
            if (result == 0) donut++;
            else if (result == 1) straight++;
            else if (result == 2) eight++;
        }

        return new int[]{newNode, donut, straight, eight};
    }

    private int find(int start) {
        Set<Integer> path = new HashSet<>();
        int cur = start;

        while (true) {
            if (visited.contains(cur)) break;
            visited.add(cur);
            path.add(cur);

            int inDeg = in.getOrDefault(cur, 0);
            int outDeg = out.getOrDefault(cur, 0);

            if (inDeg >= 2 && outDeg >= 2) return 2;

            if (outDeg == 0) return 1;

            List<Integer> nextList = graph.get(cur);
            if (nextList.isEmpty()) return 1;

            cur = nextList.get(0);

            if (path.contains(cur)) break;
        }

        return 0;
    }
}
