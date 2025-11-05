import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<Integer>[] next = new HashSet[N + 1];
        int[] count = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            next[i] = new HashSet<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) continue;

            int prev = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n - 1; j++) {
                int cur = Integer.parseInt(st.nextToken());

                if (next[prev].add(cur)) {
                    count[cur]++;
                }
                prev = cur;
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (count[i] == 0) q.offer(i);
        }

        int visited = 0;
        while (!q.isEmpty()) {
            int n = q.poll();
            sb.append(n).append('\n');
            visited++;

            for (int v : next[n]) {
                if (--count[v] == 0) q.offer(v);
            }
        }

        if (visited != N) {
            System.out.print(0);
        } else {
            System.out.print(sb.toString());
        }
    }
}
