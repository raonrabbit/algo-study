import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static HashSet<Integer>[] out;
    static List<Integer>[] children;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        out = new HashSet[N + 1];
        children = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            out[i] = new HashSet<>();
            children[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            out[b].add(a);
            children[a].add(b);
        }

        List<Integer> students = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            students.add(i);
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> result = new ArrayList<>();

        while (!students.isEmpty()) {
            Iterator<Integer> it = students.iterator();

            while (it.hasNext()) {
                int cur = it.next();
                if (out[cur].isEmpty()) {
                    result.add(cur);
                    it.remove();
                    for (int child : children[cur]) {
                        out[child].remove(cur);
                    }
                    break;
                }
            }
        }

        for (int x : result) {
            sb.append(x).append(' ');
        }
        System.out.print(sb.toString().trim());
    }
}
