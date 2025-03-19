import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while(true) {
            boolean moved = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && bfs(i, j)) {
                        moved = true;
                    }
                }
            }
            if (!moved) break;
            result++;
        }
        System.out.print(result);
    }

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static boolean bfs(int y, int x){
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> union = new ArrayList<>();

        queue.add(new int[]{y, x});
        union.add(new int[]{y, x});
        visited[y][x] = true;

        int sum = map[y][x];
        int count = 1;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cy = cur[0], cx = cur[1];

            for(int i = 0; i < 4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if(ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx]) continue;

                int diff = map[cy][cx] - map[ny][nx];
                if(diff < 0) diff = -diff;

                if(diff >= L && diff <= R){
                    queue.add(new int[]{ny, nx});
                    union.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                    sum += map[ny][nx];
                    count++;
                }
            }
        }

        if(count == 1) return false;

        int avg = sum / count;
        for(int[] pos : union){
            map[pos[0]][pos[1]] = avg;
        }

        return true;
    }
}
