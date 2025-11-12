import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] map = new char[h + 2][w + 2];

            for (int i = 0; i < h + 2; i++) {
                Arrays.fill(map[i], '.');
            }

            for(int i = 1; i <= h; i++){
                String s = br.readLine();
                for(int j = 1; j <= w; j++){
                    map[i][j] = s.charAt(j - 1);
                }
            }

            String keys = br.readLine();
            boolean[] visited_keys = new boolean[26];
            if (!keys.equals("0")) {
                for (int i = 0; i < keys.length(); i++) {
                    char c = keys.charAt(i);
                    visited_keys[c - 'a'] = true;
                }
            }

            Map<Integer, List<int[]>> paths = new HashMap<>();

            Queue<int[]> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[h + 2][w + 2];
            q.add(new int[]{0, 0});
            visited[0][0] = true;

            int result = 0;

            while(!q.isEmpty()){
                int size = q.size();

                for(int i = 0; i < size; i++){
                    int[] cur = q.poll();

                    for(int[] dir : dirs){
                        int nh = cur[0] + dir[0];
                        int nw = cur[1] + dir[1];
                        if(nh < 0 || nh >= h + 2 || nw < 0 || nw >= w + 2 || visited[nh][nw] || map[nh][nw] == '*') continue;
                        visited[nh][nw] = true;

                        char c = map[nh][nw];
                        if(c == '$') {
                            q.add(new int[]{nh, nw});
                            result++;
                            continue;
                        }

                        if(c == '.') {
                            q.add(new int[]{nh, nw});
                            continue;
                        }

                        if(c >= 'A' && c <= 'Z') {
                            if(visited_keys[c - 'A']){
                                q.add(new int[]{nh, nw});
                            } else {
                                paths.computeIfAbsent(c - 'A', v -> new ArrayList<>()).add(new int[]{nh, nw});
                                visited[nh][nw] = false;
                            }
                            continue;
                        }

                        q.add(new int[]{nh, nw});
                        if(visited_keys[c - 'a']) continue;
                        visited_keys[c - 'a'] = true;

                        if(paths.containsKey(c - 'a')) {
                            List<int[]> l = paths.get(c - 'a');

                            for (int[] n : l) {
                                q.add(n);
                                visited[n[0]][n[1]] = true;
                            }
                        }
                    }
                }
            }
            sb.append(result).append('\n');
        }

        System.out.print(sb);
    }
}
