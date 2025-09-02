import java.util.*;

class Solution {
    public static class Node {
        public List<Integer> parents;
        public List<Integer> childs;

        public Node(){
            parents = new ArrayList<>();
            childs = new ArrayList<>();
        }
    }

    public int solution(int n, int[][] results) {
        Node[] fighters = new Node[n + 1];

        for(int i = 1; i <= n; i++){
            fighters[i] = new Node();
        }

        for(int[] result : results) {
            fighters[result[0]].childs.add(result[1]);
            fighters[result[1]].parents.add(result[0]);
        }

        int answer = 0;
        for(int i = 1; i <= n; i++){
            Queue<Integer> queue = new ArrayDeque<>();

            queue.add(i);
            int temp = 0;
            boolean[] visited = new boolean[n + 1];
            visited[i] = true;

            while(!queue.isEmpty()){
                int cur = queue.poll();
                for(int j : fighters[cur].childs){
                    if(visited[j]) continue;
                    visited[j] = true;
                    queue.add(j);
                    temp++;
                }
            }

            queue.add(i);
            while(!queue.isEmpty()){
                int cur = queue.poll();
                for(int j : fighters[cur].parents){
                    if(visited[j]) continue;
                    visited[j] = true;
                    queue.add(j);
                    temp++;
                }
            }
            if(temp == n - 1) answer++;
        }

        return answer;
    }
}