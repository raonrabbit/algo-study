import java.util.*;

class Solution {
    static Map<Integer, Queue<Integer>> map;
    static Set<Integer> visited;

    public int[] solution(int[] nodes, int[][] edges) {
        map = new HashMap<>();
        visited = new HashSet<>();
        int a = 0;
        int b = 0;
        for(int node : nodes){
            map.put(node, new ArrayDeque<>());
        }

        for(int[] edge : edges){
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        for(int key : map.keySet()) {
            if(visited.contains(key)) continue;
            int result = bfs(key);
            switch(result){
                case 2:
                    a++;
                    b++;
                    break;
                case 1:
                    a++;
                    break;
                case 0:
                    b++;
                    break;
            }
        }

        int[] answer = new int[2];
        answer[0] = a;
        answer[1] = b;
        return answer;
    }

    public int bfs(int n) {
        if(map.get(n).size() == 0){
            visited.add(n);
            if(n % 2 == 0) return 1;
            else return 0;
        }

        Queue<Integer> q = new ArrayDeque<>();

        q.add(n);
        visited.add(n);

        int a = 0;
        int b = 0;

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){
                int temp = q.poll();
                boolean flag = (temp + (map.get(temp).size() - 1)) % 2 == 0;

                if(flag) a++;
                else b++;

                for(int t : map.get(temp)){
                    if(visited.contains(t)) continue;
                    visited.add(t);
                    q.add(t);
                }
            }
        }
        if(a == 1 && b == 1) return 2;
        if(a == 1) return 0;
        if(b == 1) return 1;
        return -1;
    }
}