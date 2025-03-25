import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        SortedMap<Integer, SortedSet<Integer>> qs = new TreeMap<>();
        int[] arr = new int[100001];

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a] = b;
            if(qs.containsKey(b)){
                qs.get(b).add(a);
            } else {
                qs.put(b, new TreeSet<>());
                qs.get(b).add(a);
            }
        }

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int a, b;
            switch(command){
                case "add":
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    arr[a] = b;
                    if(qs.containsKey(b)){
                        qs.get(b).add(a);
                    } else {
                        qs.put(b, new TreeSet<>());
                        qs.get(b).add(a);
                    }
                    break;
                case "solved":
                    a = Integer.parseInt(st.nextToken());
                    SortedSet<Integer> set = qs.get(arr[a]);
                    set.remove(a);
                    if(set.isEmpty()){
                        qs.remove(arr[a]);
                    }
                    break;
                case "recommend":
                    a = Integer.parseInt(st.nextToken());
                    if(a > 0){
                        sb.append(qs.get(qs.lastKey()).last()).append('\n');
                    } else {
                        sb.append(qs.get(qs.firstKey()).first()).append('\n');
                    }
            }
        }
        System.out.println(sb);
    }
}
