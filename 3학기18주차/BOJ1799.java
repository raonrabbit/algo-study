import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] dirs = new int[][] {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public static int n;
    public static boolean[][] map;
    public static boolean[][] bshop;
    public static int max;

    public static void DFS(int y, int x, boolean is_black, int count){
        if(count > max) {
            max = count;
        }

        if(y >= n) {
            return;
        }

        if(x >= n) {
            if(is_black) {
                DFS(y + 1, (y + 1) % 2, is_black, count);
            } else {
                DFS(y + 1, 1 - ((y + 1) % 2), is_black, count);
            }
            return;
        }

        if(map[y][x] && can_move(y, x)) {
            bshop[y][x] = true;
            DFS(y, x + 2, is_black, count + 1);
            bshop[y][x] = false;
        }

        DFS(y, x + 2, is_black, count);
    }

    public static boolean can_move(int y, int x){
        for(int[] dir: dirs){
            int ny = y + dir[0];
            int nx = x + dir[1];
            while(!out_of_map(ny, nx)){
                if(bshop[ny][nx]) return false;
                ny += dir[0];
                nx += dir[1];
            }
        }
        return true;
    }

    public static boolean out_of_map(int y, int x){
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new boolean[n][n];
        bshop = new boolean[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        int result = 0;
        max = 0;
        DFS(0, 0, true, 0);
        result += max;

        max = 0;
        DFS(0, 1, false, 0);
        result += max;

        System.out.print(result);
    }
}