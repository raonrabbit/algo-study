import java.util.*;

class Solution {
    public static boolean[][] map;
    public static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static int[][] simple_dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static boolean isOutOfMap(int y, int x){
        return y < 0 || y >= 200 || x < 0 || x >= 200;
    }

    public static class Node {
        public int y, x;

        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static boolean isEdge (int y, int x){
        for(int[] dir : dirs) {
            int newY = y + dir[0];
            int newX = x + dir[1];

            if(isOutOfMap(newY, newX) || !map[newY][newX]) return true;
        }
        return false;
    }

    public static int BFS(int startY, int startX, int targetY, int targetX) {
        boolean [][] visited = new boolean[200][200];
        Queue<Node> q = new ArrayDeque<>();

        visited[startY][startX] = true;
        q.add(new Node(startY, startX));

        int answer = 1;

        while(!q.isEmpty()) {
            int qSize = q.size();

            for(int i = 0; i < qSize; i++) {
                Node n = q.poll();
                int y = n.y;
                int x = n.x;

                for(int[] dir : simple_dirs) {
                    int newY = n.y + dir[0];
                    int newX = n.x + dir[1];
                    if(newY == targetY && newX == targetX) {
                        return answer;
                    }

                    if(isOutOfMap(newY, newX) || visited[newY][newX] || !map[newY][newX]) continue;
                    visited[newY][newX] = true;

                    if(!isEdge(newY, newX)) continue;

                    q.add(new Node(newY, newX));
                }
            }
            answer++;
        }

        return answer;
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new boolean[200][200];

        for(int[] rect : rectangle){
            int ty = rect[1] * 2;
            int by = rect[3] * 2;
            int lx = rect[0] * 2;
            int rx = rect[2] * 2;
            for(int i = ty; i <= by; i++){
                for(int j = lx; j <= rx; j++){
                    map[i][j] = true;
                }
            }
        }

        int answer = BFS(characterY * 2, characterX * 2, itemY * 2, itemX * 2);

        return answer / 2;
    }
}