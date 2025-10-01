import java.util.*;

class Solution {
    static int width;
    static int minMove;
    static int[][] clocks;
    static int[][] dirs = new int[][]{{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean outOfMap(int y, int x){
        return y < 0 || y >= width || x < 0 || x >= width;
    }

    public void spin(int[][] arr, int y, int x, int amount){
        for(int[] dir : dirs){
            int newY = y + dir[0];
            int newX = x + dir[1];

            if(outOfMap(newY, newX)) continue;
            int newValue = arr[newY][newX] + amount;

            newValue = (newValue + 4) % 4;

            arr[newY][newX] = newValue;
        }
    }

    public void DFS(int depth, int spinCount){
        if(depth == width) {
            int[][] clocksCopy = new int[width][width];
            int spinCountTemp = spinCount;
            for(int i = 0; i < width; i++){
                clocksCopy[i] = clocks[i].clone();
            }
            for(int i = 0; i < width - 1; i++){
                for(int j = 0; j < width; j++){
                    if(clocksCopy[i][j] == 0) continue;
                    int spinAmount = 4 - clocksCopy[i][j];
                    spin(clocksCopy, i + 1, j, spinAmount);
                    spinCountTemp += spinAmount;
                }
            }

            for(int i = 0; i < width; i++){
                for(int j = 0; j < width; j++){
                    if(clocksCopy[i][j] != 0) return;
                }
            }

            minMove = Math.min(minMove, spinCountTemp);
            return;
        }

        spin(clocks, 0, depth, 1);
        DFS(depth + 1, spinCount + 1);
        spin(clocks, 0, depth, -1);

        spin(clocks, 0, depth, 2);
        DFS(depth + 1, spinCount + 2);
        spin(clocks, 0, depth, -2);

        spin(clocks, 0, depth, 3);
        DFS(depth + 1, spinCount + 3);
        spin(clocks, 0, depth, -3);

        DFS(depth + 1, spinCount);
    }

    public int solution(int[][] clockHands) {
        clocks = clockHands;
        width = clockHands.length;
        minMove = Integer.MAX_VALUE;

        DFS(0, 0);
        return minMove;
    }
}