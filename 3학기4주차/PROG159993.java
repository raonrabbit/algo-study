import java.util.*;

class Solution {
    public static char[][] char_maps;
    public static int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public static int wLen, hLen;

    public static class Coord {
        public int y, x;
        public Coord(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public int BFS(Coord start, Coord end){
        boolean[][] visited = new boolean[hLen][wLen];

        for(int i = 0; i < hLen; i++){
            for(int j = 0; j < wLen; j++){
                visited[i][j] = char_maps[i][j] == 'X' ? true : false;
            }
        }

        Queue<Coord> queue = new ArrayDeque<>();

        queue.add(new Coord(start.y, start.x));
        visited[start.y][start.x] = true;

        int count = 0;

        while(!queue.isEmpty()){
            int qSIze = queue.size();
            count++;

            for(int i = 0; i < qSIze; i++){
                Coord curBlock = queue.poll();
                for(int dir = 0; dir < 4; dir++){
                    int newY = curBlock.y + dirs[dir][0];
                    int newX = curBlock.x + dirs[dir][1];

                    if(newY == end.y && newX == end.x) return count;

                    if(newY < 0 || newY >= hLen || newX < 0 || newX >= wLen || visited[newY][newX]) continue;

                    visited[newY][newX] = true;
                    queue.add(new Coord(newY, newX));
                }
            }
        }

        return -1;
    }

    public int solution(String[] maps) {
        hLen = maps.length;
        wLen = maps[0].length();

        char_maps = new char[hLen][wLen];
        Coord start = new Coord(0, 0), lever = new Coord(0, 0), end = new Coord(0, 0);

        for(int i = 0; i < hLen; i++){
            for(int j = 0; j < wLen; j++){
                char_maps[i][j] = maps[i].charAt(j);

                if(char_maps[i][j] == 'S'){
                    start = new Coord(i, j);
                    continue;
                }

                if(char_maps[i][j] == 'L'){
                    lever = new Coord(i, j);
                }

                if(char_maps[i][j] == 'E'){
                    end = new Coord(i, j);
                }
            }
        }

        int start_to_lever = BFS(start, lever);
        if(start_to_lever == -1) return -1;
        int lever_to_end = BFS(lever, end);
        if(lever_to_end == -1) return -1;

        return start_to_lever + lever_to_end;
    }
}