import java.util.*;

class Solution {
    public static int[][] map;
    public static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int h, w;

    public static class Coord{
        public int y, x;
        public Coord(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static boolean outOfMap(int y, int x){
        return y < 0 || y >= h || x < 0 || x >= w;
    }

    public static int BFS(){
        boolean[][] visited = new boolean[h][w];
        visited[0][0] = true;

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(map[i][j] == 0) visited[i][j] = true;
            }
        }

        Queue<Coord> queue = new ArrayDeque<>();
        queue.add(new Coord(0, 0));
        int result = 1;

        while(!queue.isEmpty()){
            result++;
            int size = queue.size();

            for(int i = 0; i < size; i++){
                Coord c = queue.poll();
                for(int[] dir : dirs){
                    int newY = c.y + dir[0];
                    int newX = c.x + dir[1];
                    if(outOfMap(newY, newX) || visited[newY][newX]) continue;
                    visited[newY][newX] = true;
                    queue.add(new Coord(newY, newX));

                    if(newY == h - 1 && newX == w - 1) return result;
                }
            }
        }

        return -1;
    }

    public int solution(int[][] maps) {
        h = maps.length;
        w = maps[0].length;
        map = maps;
        int answer = BFS();
        return answer;
    }
}