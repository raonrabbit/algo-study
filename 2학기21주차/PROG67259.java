import java.util.*;

class Solution {
    public static int w;
    public static int minPrice;

    public static boolean[][] visited;
    public static int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static int[][][] cost;

    public static void DFS(int dir, int price, int y, int x){
        if(price >= minPrice) return;

        if(y == w && x == w){
            minPrice = Math.min(price, minPrice);
            return;
        }

        for(int i = dir; i < dir + 4; i++){
            int temp = i % 4;
            if(dir == -1) temp += 1;
            int newY = y + dirs[temp][0];
            int newX = x + dirs[temp][1];

            if(visited[newY][newX]) continue;

            int addPrice;

            if(temp == dir || dir == -1) addPrice = 100;
            else addPrice = 600;

            if(cost[temp][newY][newX] <= price + addPrice) continue;
            else cost[temp][newY][newX] = price + addPrice;

            price += addPrice;
            visited[newY][newX] = true;
            DFS(temp, price, newY, newX);
            visited[newY][newX] = false;
            price -= addPrice;
        }
    }

    public int solution(int[][] board) {
        w = board.length;
        minPrice = Integer.MAX_VALUE;
        cost = new int[4][w + 2][w + 2];

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < w + 2; j++){
                for(int p = 0; p < w + 2; p++){
                    cost[i][j][p] = Integer.MAX_VALUE;
                }
            }
        }

        visited = new boolean[w + 2][w + 2];

        for(int i = 0; i < w + 2; i++){
            visited[i][0] = true;
            visited[i][w + 1] = true;
            visited[0][i] = true;
            visited[w + 1][i] = true;
        }

        for(int i = 0; i < w; i++){
            for(int j = 0; j < w; j++){
                visited[i + 1][j + 1] = (board[i][j] == 1);
            }
        }

        visited[1][1] = true;
        DFS(-1, 0, 1, 1);
        return minPrice;
    }
}