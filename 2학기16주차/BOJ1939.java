import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] bridge;
    static HashMap<Integer, Integer>[] bridges;
    static boolean[] visited;
    static int minWeight = Integer.MAX_VALUE, maxWeight = Integer.MIN_VALUE;
    static int start, end;

    public static boolean DFS(int cur, int limit) {
        if (cur == end) return true;
        for (int next : bridge[cur]) {
            if (!visited[next] && bridges[cur].getOrDefault(next, -1) >= limit) {
                visited[next] = true;
                if (DFS(next, limit)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bridge = new ArrayList[N + 1];
        bridges = new HashMap[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            bridge[i] = new ArrayList<>();
            bridges[i] = new HashMap<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(bridges[a].containsKey(b)){
                bridges[a].replace(b, Math.max(bridges[a].get(b), w));
            } else {
                bridges[a].put(b, w);
            }

            if(bridges[b].containsKey(a)){
                bridges[b].replace(a, Math.max(bridges[b].get(a), w));
            } else {
                bridges[b].put(a, w);
            }

            bridge[a].add(b);
            bridge[b].add(a);

            minWeight = Math.min(minWeight, w);
            maxWeight = Math.max(maxWeight, w);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end   = Integer.parseInt(st.nextToken());

        int low = minWeight, high = maxWeight;
        int result = 0;
        while (low <= high) {
            int mid = (low + high) / 2;

            Arrays.fill(visited, false);
            visited[start] = true;
            if (DFS(start, mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(result);
    }
}
