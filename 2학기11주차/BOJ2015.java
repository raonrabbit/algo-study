import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        long result = 0;
        long sum = 0;
        HashMap<Long, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            sum += Integer.parseInt(st.nextToken());
            result += map.getOrDefault(sum - K, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            if(sum == K) result++;
        }

        System.out.print(result);
    }
}