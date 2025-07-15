import java.util.*;

class Solution {
    public static int h, w, targetY, targetX, maxMoveCount;
    public static boolean done;
    public static StringBuilder sb;

    public static int[][] dirs = new int[][] {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};

    public static char[] dirChars = new char[] {'d', 'l', 'r', 'u'};

    public static void DFS(int depth, int yPos, int xPos){

        if(depth == maxMoveCount){
            if(yPos == targetY && xPos == targetX){
                done = true;
            }

            return;
        }

        for(int i = 0; i < 4; i++){
            int newY = yPos + dirs[i][0];
            int newX = xPos + dirs[i][1];

            if(newY < 0 || newY >= h ||
                    newX < 0 || newX >= w ||
                    (maxMoveCount - (depth + 1) < Math.abs(newY - targetY) + Math.abs(newX - targetX))) continue;

            sb.append(dirChars[i]);

            DFS(depth + 1, newY, newX);

            if(!done) sb.deleteCharAt(depth);
            else return;
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        h = n;
        w = m;
        int startY = x - 1;
        int startX = y - 1;
        targetY = r - 1;
        targetX = c - 1;

        int dist = Math.abs(startY - targetY) + Math.abs(startX - targetX);
        if(dist > k || (dist - k) % 2 != 0) return "impossible";

        maxMoveCount = k;
        sb = new StringBuilder();

        DFS(0, startY, startX);

        return sb.toString();
    }
}