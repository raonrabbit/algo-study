import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] map;
    public static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static boolean isOutOfMap(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= N;
    }

    public static void spin(int y, int x, int w) {
        for (int i = 0; i < w; i++) {
            for (int j = i + 1; j < w; j++) {
                int temp = map[y + i][x + j];
                map[y + i][x + j] = map[y + j][x + i];
                map[y + j][x + i] = temp;
            }
        }
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < w / 2; j++) {
                int temp = map[y + i][x + j];
                map[y + i][x + j] = map[y + i][x + w - 1 - j];
                map[y + i][x + w - 1 - j] = temp;
            }
        }
    }

    public static void spinMap(int power) {
        int w = (int)Math.pow(2, power);
        int n = N / w;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int y = i * w;
                int x = j * w;
                spin(y, x, w);
            }
        }
    }

    public static void meltIce() {
        boolean[][] meltingMap = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 0) continue;

                int count = 0;
                for(int[] dir : dirs){
                    int newY = i + dir[0];
                    int newX = j + dir[1];

                    if(isOutOfMap(newY, newX)) continue;
                    if(map[newY][newX] > 0) count++;
                }
                if(count < 3) meltingMap[i][j] = true;
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = meltingMap[i][j] ? (map[i][j] - 1) : map[i][j];
            }
        }
    }

    public static int countBiggestIceBlock() {
        boolean[][] visited = new boolean[N][N];
        int max = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 0 || visited[i][j]) continue;

                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[]{i, j});
                visited[i][j] = true;

                int count = 0;
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    int y = cur[0], x = cur[1];

                    count++;
                    for(int[] dir : dirs){
                        int newY = y + dir[0];
                        int newX = x + dir[1];

                        if(isOutOfMap(newY, newX)
                                || visited[newY][newX]
                                || map[newY][newX] == 0) continue;

                        visited[newY][newX] = true;
                        q.add(new int[]{newY, newX});
                    }
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }

    public static int countTotalIce(){
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                count += map[i][j];
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = (int)Math.pow(2, Integer.parseInt(st.nextToken()));

        int Q = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < Q; i++){
            int power = Integer.parseInt(st.nextToken());
            spinMap(power);
            meltIce();
        }

        System.out.println(countTotalIce());
        System.out.println(countBiggestIceBlock());
    }
}
