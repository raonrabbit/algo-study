import java.util.*;

class Solution {

    public int[][] map;
    public int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public int ySize, xSize;

    public static class Node{
        public int y;
        public int x;

        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public int BFS(int target) {
        boolean[][] visited = new boolean[ySize + 2][xSize + 2];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));
        visited[0][0] = true;
        int deleteCount = 0;
        Queue<Node> deleteNodes = new ArrayDeque<>();

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                int curY = cur.y;
                int curX = cur.x;

                for (int[] dir : dirs) {
                    int ny = curY + dir[0];
                    int nx = curX + dir[1];

                    if (ny < 0 || ny > ySize + 1 || nx < 0 || nx > xSize + 1) continue;
                    if (visited[ny][nx]) continue;

                    visited[ny][nx] = true;
                    if(map[ny][nx] == target) {
                        deleteCount++;
                        deleteNodes.add(new Node(ny, nx));
                        continue;
                    }
                    if(map[ny][nx] == 0) q.add(new Node(ny, nx));
                }
            }
        }

        for(Node n : deleteNodes){
            map[n.y][n.x] = 0;
        }

        return deleteCount;
    }

    public int solution(String[] storage, String[] requests) {
        ySize = storage.length;
        xSize = storage[0].length();
        int result = ySize * xSize;

        map = new int[ySize + 2][xSize + 2];
        for (int i = 1; i <= ySize; i++) {
            for (int j = 1; j <= xSize; j++) {
                map[i][j] = storage[i - 1].charAt(j - 1) - 'A' + 1;
            }
        }

        for(String request : requests){
            char ch = request.charAt(0);
            int target = ch - 'A' + 1;
            int deleteCount = 0;
            if (request.length() == 2) {
                for(int i = 1; i <= ySize; i++){
                    for(int j = 1; j <= xSize; j++){
                        if(map[i][j] == target) {
                            map[i][j] = 0;
                            deleteCount++;
                        }
                    }
                }
            } else {
                deleteCount = BFS(target);
            }
            result -= deleteCount;
        }

        return result;
    }
}