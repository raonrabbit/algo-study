import java.util.*;

class Solution {
    public Node[] nodes;
    public static class Node {
        public int s;
        public List<Integer> ends;

        public Node(int n){
            s = n;
            ends = new ArrayList<Integer>();
        }
    }

    public int solution(int n, int[][] edge) {
        nodes = new Node[n + 1];

        for(int i = 1; i <= n; i++){
            nodes[i] = new Node(i);
        }

        for(int[] line : edge){
            int s = line[0];
            int e = line[1];

            nodes[s].ends.add(e);
            nodes[e].ends.add(s);
        }

        int answer = bfs();
        return answer;
    }

    public int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[nodes.length + 1];
        visited[1] = true;
        q.add(nodes[1]);
        int dist = 0;
        int qSize = 0;

        while(!q.isEmpty()) {
            qSize = q.size();

            for(int i = 0; i < qSize; i++){
                Node n = q.poll();
                dist++;

                for(int e : n.ends){
                    if(visited[e]) continue;
                    visited[e] = true;
                    q.add(nodes[e]);
                }
            }
        }

        return qSize;
    }
}