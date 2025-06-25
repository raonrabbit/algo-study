import java.util.*;

class Solution {
    public static class Node{
        public int x, y;
        public Node(int y, int x){
            this.x = x;
            this.y = y;
        }

        public boolean equal(Node o){
            return (o.y == this.y && o.x == this.x);
        }
    }

    public static boolean[][] redVisited;
    public static boolean[][] blueVisited;
    public static int w, h;
    public static int minMove;
    public static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static Node redStart, blueStart, redEnd, blueEnd;

    public void DFS(Node redPos, Node bluePos, int move){
        if(move >= minMove) return;

        if(redPos.equal(redEnd) && bluePos.equal(blueEnd)){
            minMove = Math.min(move, minMove);
            return;
        }

        if(!redPos.equal(redEnd)){
            for(int[] dir : dirs){
                Node newRedPos = new Node(redPos.y + dir[0], redPos.x + dir[1]);
                if(newRedPos.y < 0 || newRedPos.y >= h || newRedPos.x < 0 || newRedPos.x >= w || redVisited[newRedPos.y][newRedPos.x]) continue;
                redVisited[newRedPos.y][newRedPos.x] = true;
                if(!bluePos.equal(blueEnd)){
                    if(newRedPos.equal(redEnd)) blueVisited[newRedPos.y][newRedPos.x] = true;
                    for(int[] dir2 : dirs){
                        Node newBluePos = new Node(bluePos.y + dir2[0], bluePos.x + dir2[1]);
                        if(newBluePos.y < 0 || newBluePos.y >= h || newBluePos.x < 0 || newBluePos.x >= w || newBluePos.equal(bluePos) || blueVisited[newBluePos.y][newBluePos.x] || newBluePos.equal(newRedPos) || (newRedPos.equal(bluePos) && newBluePos.equal(redPos))) continue;
                        blueVisited[newBluePos.y][newBluePos.x] = true;
                        if(newBluePos.equal(blueEnd)) redVisited[blueEnd.y][blueEnd.x] = true;
                        DFS(newRedPos, newBluePos, move + 1);
                        blueVisited[newBluePos.y][newBluePos.x] = false;
                        if(newBluePos.equal(blueEnd)) redVisited[blueEnd.y][blueEnd.x] = false;
                    }
                    if(newRedPos.equal(redEnd)) blueVisited[newRedPos.y][newRedPos.x] = false;
                } else {
                    if(!newRedPos.equal(bluePos)){
                        DFS(newRedPos, bluePos, move + 1);
                    }
                }
                redVisited[newRedPos.y][newRedPos.x] = false;
            }
        } else {
            for(int[] dir : dirs){
                Node newBluePos = new Node(bluePos.y + dir[0], bluePos.x + dir[1]);
                if(newBluePos.y < 0 || newBluePos.y >= h || newBluePos.x < 0 || newBluePos.x >= w || blueVisited[newBluePos.y][newBluePos.x] || newBluePos.equal(redPos)) continue;
                blueVisited[newBluePos.y][newBluePos.x] = true;
                if(newBluePos.equal(blueEnd)) redVisited[blueEnd.y][blueEnd.x] = true;
                DFS(redPos, newBluePos, move + 1);
                blueVisited[newBluePos.y][newBluePos.x] = false;
                if(newBluePos.equal(blueEnd)) redVisited[blueEnd.y][blueEnd.x] = false;
            }
        }
    }

    public int solution(int[][] maze) {
        h = maze.length;
        w = maze[0].length;
        minMove = Integer.MAX_VALUE;

        redVisited = new boolean[h][w];
        blueVisited = new boolean[h][w];

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(maze[i][j] == 1){
                    redStart = new Node(i, j);
                    redVisited[i][j] = true;
                    continue;
                }

                if(maze[i][j] == 2){
                    blueStart = new Node(i, j);
                    blueVisited[i][j] = true;
                    continue;
                }

                if(maze[i][j] == 3){
                    redEnd = new Node(i, j);
                    continue;
                }

                if(maze[i][j] == 4){
                    blueEnd = new Node(i, j);
                    continue;
                }

                if(maze[i][j] == 5){
                    blueVisited[i][j] = true;
                    redVisited[i][j] = true;
                }
            }
        }
        DFS(redStart, blueStart, 0);
        if(minMove == Integer.MAX_VALUE) minMove = 0;
        return minMove;
    }
}