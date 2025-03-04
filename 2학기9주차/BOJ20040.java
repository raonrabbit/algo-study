import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];

        for(int i = 0; i < N; i++){
            parent[i] = i;
        }
        int result = -1;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(find(a) == find(b)){
                result = i + 1;
                break;
            } else {
                union(a, b);
            }
        }

        if(result == -1) result = 0;
        System.out.print(result);
    }
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a != b){
            parent[b] = a;
        }
    }

    public static int find(int x){
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
}
