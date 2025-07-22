import java.util.*;

class Solution {
    public static int landW, landH;
    public static int[][] temp_land;
    public static int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public static Map<Integer, Integer> oilMap;

    public static class Pos {
        public int y, x;

        public Pos(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static void BFS(int y, int x){
        Set<Integer> set = new HashSet<>();
        Queue<Pos> q = new ArrayDeque<>();

        q.add(new Pos(y, x));
        temp_land[y][x] = 0;
        set.add(x);
        int count = 1;

        while(!q.isEmpty()){
            int size = q.size();

            for(int t = 0; t < size; t++){
                Pos n = q.poll();

                for(int[] dir : dirs){
                    int newY = n.y + dir[0];
                    int newX = n.x + dir[1];

                    if(newY < 0 || newY >= landH || newX < 0 || newX >= landW || temp_land[newY][newX] == 0) continue;
                    temp_land[newY][newX] = 0;
                    q.add(new Pos(newY, newX));
                    set.add(newX);
                    count++;
                }
            }
        }

        for(int m : set){
            oilMap.put(m, oilMap.getOrDefault(m, 0) + count);
        }
    }


    public int solution(int[][] land) {
        landW = land[0].length;
        landH = land.length;
        temp_land = land;
        oilMap = new HashMap<>();
        int groupNumber = 1;

        for(int i = 0; i < landH; i++){
            for(int j = 0; j < landW; j++){
                if(land[i][j] == 0) continue;
                BFS(i, j);
            }
        }

        int max = 0;

        for(int key : oilMap.keySet()){
            int m = oilMap.get(key);
            max = Math.max(max, m);
        }

        return max;
    }
}