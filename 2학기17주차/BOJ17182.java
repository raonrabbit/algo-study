import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] stars;
    static HashSet<Integer>[][] visited;
    static int min;
    static boolean[] visitedStars;

    public static void DFS(int depth, int n, int value){
        if (value > min) return;
        if (depth >= N){
            min = value;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visitedStars[i]) continue;

            Queue<Integer> q = new ArrayDeque<>();
            int count = 0;

            for (int t : visited[n][i]) {
                if (!visitedStars[t]) {
                    visitedStars[t] = true;
                    q.add(t);
                    count++;
                }
            }

            if (!visitedStars[i]) {
                visitedStars[i] = true;
                DFS(depth + count + 1, i, value + stars[n][i]);
                visitedStars[i] = false;
            }

            for (int t : q) {
                visitedStars[t] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        stars = new int[N][N];
        visited = new HashSet[N][N];
        min = Integer.MAX_VALUE;
        visitedStars = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                stars[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = new HashSet<>();
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (stars[i][j] > stars[i][k] + stars[k][j]) {
                        stars[i][j] = stars[i][k] + stars[k][j];

                        visited[i][j].clear();
                        visited[i][j].add(k);
                        visited[i][j].addAll(visited[i][k]);
                        visited[i][j].addAll(visited[k][j]);
                    }
                }
            }
        }

        visitedStars[K] = true;
        DFS(1, K, 0);
        System.out.println(min);
    }
}
