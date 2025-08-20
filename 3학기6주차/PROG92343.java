import java.util.*;

class Solution {
    public static int max;
    public Node[] nodes;

    public static class Node {
        public boolean isSheep;
        public ArrayList<Node> childs;

        public Node(int num){
            if(num == 0) this.isSheep = true;
            else this.isSheep = false;
            childs = new ArrayList<>();
        }
    }

    public static void DFS(ArrayList<Node> childs, int sheepCount, int wolfCount) {
        for(int i = 0; i < childs.size(); i++){
            if(!childs.get(i).isSheep && sheepCount <= (wolfCount + 1)) continue;
            ArrayList<Node> tempChilds = new ArrayList<>(childs.get(i).childs);
            for(int j = 0; j < childs.size(); j++){
                if(i == j) continue;
                tempChilds.add(childs.get(j));
            }
            if(childs.get(i).isSheep) {
                max = Math.max(max, sheepCount + 1);
                DFS(tempChilds, sheepCount + 1, wolfCount);
            } else {
                DFS(tempChilds, sheepCount, wolfCount + 1);
            }
        }
    }

    public int solution(int[] info, int[][] edges) {
        nodes = new Node[info.length];
        max = 1;

        for(int i = 0; i < info.length; i++){
            nodes[i] = new Node(info[i]);
        }

        for(int[] edge: edges){
            nodes[edge[0]].childs.add(nodes[edge[1]]);
        }

        DFS(new ArrayList<Node>(nodes[0].childs), 1, 0);

        return max;
    }
}